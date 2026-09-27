import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// DataLoader rebuilds the in-memory system from CSV files at startup.
// Why this class is needed:
// - It restores users, events, and bookings so data is not lost between runs.
// - It reconstructs object references (booking -> user/event objects) from IDs.
// - It restores waitlist ordering so promotions remain first-come, first-served.
public class DataLoader {

    // Loads the full application state in dependency order:
    // 1) users, 2) events, 3) bookings (bookings depend on users/events),
    // then computes the next booking ID.
    public SystemState loadSystemState() {
        ArrayList<User> loadedUsers = loadUsers();
        ArrayList<Event> loadedEvents = loadEvents();
        ArrayList<Booking> loadedBookings = loadBookings(loadedUsers, loadedEvents);

        // every booking has a unique ID, so there is no 
        // conflict in operations (booking, deleting, waitlisting, promoting)
        int nextBookingId = findNextBookingId(loadedBookings);

        System.out.println("System loaded.");
        return new SystemState(loadedUsers, loadedEvents, loadedBookings, nextBookingId);
    }

    // Reads users.csv and creates concrete user objects (Student/Staff/Guest).
    // Needed so booking rules (polymorphic max bookings) still work after restart.
    public ArrayList<User> loadUsers() {
        ArrayList<User> users = new ArrayList<User>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("users.csv"));
            String line = reader.readLine(); // header

            while ((line = reader.readLine()) != null) {
                // skip empty lines
                if (line.trim().equals("")) {
                    continue;
                }

                try {
                    String[] parts = line.split(",", -1);
                    // skip invalid data (missing either userId, name, email, or userType)
                    if (parts.length < 4) {
                        continue;
                    }

                    String userId = parts[0];
                    String name = parts[1];
                    String email = parts[2];
                    String userType = parts[3];

                    User user = null;

                    // create the correct user object based on the userType
                    if (userType.equals("Student")) {
                        user = new Student(userId, name, email);
                    } else if (userType.equals("Staff")) {
                        user = new Staff(userId, name, email);
                    } else if (userType.equals("Guest")) {
                        user = new Guest(userId, name, email);
                    }

                    if (user != null) {
                        users.add(user);
                    }
                } catch (Exception rowEx) {
                    // Skip malformed rows instead of failing entire load
                }
            }

            reader.close();
        } catch (Exception e) {
            System.out.println("Error loading users from users.csv.");
        }

        return users;
    }

    // Reads events.csv and rebuilds the correct event subtype
    // (Workshop/Seminar/Concert) using the saved eventType column.
    // Needed so type-specific event data is preserved across runs.
    public ArrayList<Event> loadEvents() {
        ArrayList<Event> events = new ArrayList<Event>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("events.csv"));
            String line = reader.readLine(); // header

            while ((line = reader.readLine()) != null) {
                if (line.trim().equals("")) {
                    continue;
                }

                try {
                    String[] parts = line.split(",", -1);
                    if (parts.length < 10) {
                        continue;
                    }

                    String eventId = parts[0];
                    String title = parts[1];
                    String dateTime = parts[2];
                    String location = parts[3];
                    int capacity = Integer.parseInt(parts[4]);
                    String statusText = parts[5];
                    String eventType = parts[6];
                    String topic = parts[7];
                    String speakerName = parts[8];
                    String ageRestriction = parts[9];

                    Event event = null;

                    if (eventType.equals("Workshop")) {
                        event = new Workshop(eventId, title, dateTime, location, capacity, topic);
                    } else if (eventType.equals("Seminar")) {
                        event = new Seminar(eventId, title, dateTime, location, capacity, speakerName);
                    } else if (eventType.equals("Concert")) {
                        event = new Concert(eventId, title, dateTime, location, capacity, ageRestriction);
                    }

                    if (event != null) {
                        try {
                            event.status = EventStatus.valueOf(statusText);
                        } catch (Exception statusEx) {
                            // Keep default status if invalid
                        }
                        events.add(event);
                    }
                } catch (Exception rowEx) {
                    // Skip malformed rows instead of failing entire load
                }
            }

            reader.close();
        } catch (Exception e) {
            System.out.println("Error loading events from events.csv.");
        }

        return events;
    }

    // Reads bookings.csv and reconnects each booking to its User and Event objects.
    // Needed so booking operations (capacity checks, cancellation, promotion)
    // continue to work using live object references instead of raw IDs.
    public ArrayList<Booking> loadBookings(ArrayList<User> users, ArrayList<Event> events) {
        ArrayList<Booking> bookings = new ArrayList<Booking>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("bookings.csv"));
            String line = reader.readLine(); // header

            while ((line = reader.readLine()) != null) {
                if (line.trim().equals("")) {
                    continue;
                }

                try {
                    String[] parts = line.split(",", -1);
                    if (parts.length < 5) {
                        continue;
                    }

                    String bookingId = parts[0];
                    String userId = parts[1];
                    String eventId = parts[2];
                    String createdAt = parts[3];
                    String bookingStatus = parts[4];

                    // Booking constructor requires a user and event object, 
                    // so we need to find them by their IDs
                    User matchedUser = findUserById(users, userId);
                    Event matchedEvent = findEventById(events, eventId);

                    if (matchedUser != null && matchedEvent != null) {
                        // there is a chance that the string is invalid, 
                        // so we create an OPTIMISTIC CONFIRMED booking
                        // then we actually check the status from the CSV file
                        BookingStatus status = BookingStatus.CONFIRMED;
                        try {
                            status = BookingStatus.valueOf(bookingStatus);
                        } catch (Exception statusEx) {
                            // in case mistamatched string
                        }

                        Booking booking = new Booking(
                                bookingId,
                                matchedUser,
                                matchedEvent,
                                createdAt,
                                status
                        );

                        bookings.add(booking);
                    }
                } catch (Exception rowEx) {
                    // Skip malformed rows instead of failing entire load
                }
            }

            reader.close();
        } catch (Exception e) {
            System.out.println("Error loading bookings from bookings.csv.");
        }

        restoreWaitlistOrder(bookings);

        return bookings;
    }

    // Rebuilds waitlist sequence after loading.
    // For each event:
    // - confirmed bookings stay before waitlisted bookings
    // - waitlisted bookings are ordered by createdAt (oldest first)
    // This keeps waitlist promotion fair and consistent after restart.
    private void restoreWaitlistOrder(ArrayList<Booking> bookings) {
        // this comparator rule is to make sure we are comapring the same event
        // and that if both are waitlisted, the earliest createdAt gets priority
    Collections.sort(bookings, new Comparator<Booking>() {
        public int compare(Booking first, Booking second) {

            // make sure we are comparing the same event
            if (!first.event.eventId.equals(second.event.eventId)) {
                return 0;
            }

            // check if the bookings are waitlisted
            boolean firstWaitlisted = first.status == BookingStatus.WAITLISTED;
            boolean secondWaitlisted = second.status == BookingStatus.WAITLISTED;

            // if both are waitlisted, compare the createdAt
            if (firstWaitlisted && secondWaitlisted) {
                return first.createdAt.compareTo(second.createdAt);
            }

            // if the first is waitlisted and the second is not, return 1
            if (firstWaitlisted && !secondWaitlisted) {
                return 1;
            }

            // if the first is not waitlisted and the second is, return -1
            if (!firstWaitlisted && secondWaitlisted) {
                return -1;
            }

            return 0;
        }
    });
}

    // Utility: locate a user object from loaded users by ID.
    private User findUserById(ArrayList<User> users, String userId) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).userId.equals(userId)) {
                return users.get(i);
            }
        }
        return null;
    }

    // Utility: locate an event object from loaded events by ID.
    private Event findEventById(ArrayList<Event> events, String eventId) {
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).eventId.equals(eventId)) {
                return events.get(i);
            }
        }
        return null;
    }

    // Finds the next numeric booking ID (e.g., BK17 -> next is 18).
    // Needed to avoid duplicate booking IDs after a restart.
    private int findNextBookingId(ArrayList<Booking> bookings) {
        int maxNumber = 0;

        for (int i = 0; i < bookings.size(); i++) {
            String bookingId = bookings.get(i).bookingId;

            if (bookingId.startsWith("BK")) {
                try {
                    int number = Integer.parseInt(bookingId.substring(2));
                    if (number > maxNumber) {
                        maxNumber = number;
                    }
                } catch (Exception e) {
                }
            }
        }

        return maxNumber + 1;
    }
}

