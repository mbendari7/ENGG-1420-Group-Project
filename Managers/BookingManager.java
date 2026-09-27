import java.util.ArrayList;

public class BookingManager {
    public ArrayList<Event> allEvents = new ArrayList<>();
    public ArrayList<Booking> allBookings = new ArrayList<>();
    public int nextBookingId = 1;

    public void addEvent(Event e) {
        allEvents.add(e);
    }

    public Event getEventById(String id) {
        for (Event e : allEvents) {
            if (e.getEventId().equals(id)) {
                return e;
            }
        }
        return null;
    }

    public boolean createBooking(User user, Event event) {
        // Check if the event has space in the attendees list
        if (event.getAttendees().size() < event.getCapacity()) {
            event.getAttendees().add(user);

            // This now perfectly matches the Booking constructor above
            Booking newBooking = new Booking(nextBookingId++, user, event);

            allBookings.add(newBooking);
            return true; // Successfully booked
        } else {
            // Event is full, add user to waitlist
            event.getWaitlist().add(user);
            return false; // Signals added to waitlist
        }
    }

    public ArrayList<Event> searchEventsByTitle(String title) {
        ArrayList<Event> results = new ArrayList<>();
        for (Event e : allEvents) {
            if (e.getTitle().toLowerCase().contains(title.toLowerCase())) {
                results.add(e);
            }
        }
        return results;
    }
}