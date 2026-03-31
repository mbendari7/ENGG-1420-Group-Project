// Booking.java
public class Booking { // booking class
    String bookingId;
    User user;
    Event event;

    String createdAt; // Added to track waitlist order 

    BookingStatus status;

    public Booking(String bookingId, User user, Event event, String createdAt, BookingStatus status) { // booking constructor
        
        this.bookingId = bookingId;
        this.user = user;
        this.event = event;
        this.createdAt = createdAt;
        this.status = status;
    }
}