import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class DataLoader {

    public SystemState loadSystemState() {
        ArrayList<User> loadedUsers = loadUsers();
        ArrayList<Event> loadedEvents = loadEvents();
        ArrayList<Booking> loadedBookings = loadBookings(loadedUsers, loadedEvents);

        int nextBookingId = findNextBookingId(loadedBookings);

        System.out.println("System loaded.");
        return new SystemState(loadedUsers, loadedEvents, loadedBookings, nextBookingId);
    }

    public ArrayList<User> loadUsers() {
        ArrayList<User> users = new ArrayList<User>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("saved_users.csv"));
            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                String userId = parts[0];
                String name = parts[1];
                String email = parts[2];
                String userType = parts[3];

                User user = null;

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
            }

            reader.close();
        } catch (Exception e) {
            System.out.println("Error loading users from saved_users.csv.");
        }

        return users;
    }

    public ArrayList<Event> loadEvents() {
        ArrayList<Event> events = new ArrayList<Event>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("saved_events.csv"));
            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", -1);

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
                    event.status = EventStatus.valueOf(statusText);
                    events.add(event);
                }
            }

            reader.close();
        } catch (Exception e) {
            System.out.println("Error loading events from saved_events.csv.");
        }

        return events;
    }

    public ArrayList<Booking> loadBookings(ArrayList<User> users, ArrayList<Event> events) {
        ArrayList<Booking> bookings = new ArrayList<Booking>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("saved_bookings.csv"));
            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", -1);

                String bookingId = parts[0];
                String userId = parts[1];
                String eventId = parts[2];
                String createdAt = parts[3];
                String bookingStatus = parts[4];

                User matchedUser = findUserById(users, userId);
                Event matchedEvent = findEventById(events, eventId);

                if (matchedUser != null && matchedEvent != null) {
                    Booking booking = new Booking(
                            bookingId,
                            matchedUser,
                            matchedEvent,
                            createdAt,
                            BookingStatus.valueOf(bookingStatus)
                    );

                    bookings.add(booking);
                }
            }

            reader.close();
        } catch (Exception e) {
            System.out.println("Error loading bookings from saved_bookings.csv.");
        }

        return bookings;
    }

    private User findUserById(ArrayList<User> users, String userId) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).userId.equals(userId)) {
                return users.get(i);
            }
        }
        return null;
    }

    private Event findEventById(ArrayList<Event> events, String eventId) {
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).eventId.equals(eventId)) {
                return events.get(i);
            }
        }
        return null;
    }

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
