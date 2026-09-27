import java.io.Serializable;

public class Booking implements Serializable {
    private int bookingId;
    private User user;
    private Event event;

    // This constructor matches what BookingManager is trying to use
    public Booking(int bookingId, User user, Event event) {
        this.bookingId = bookingId;
        this.user = user;
        this.event = event;
    }

    public int getBookingId() {
        return bookingId;
    }

    public User getUser() {
        return user;
    }

    public Event getEvent() {
        return event;
    }
}