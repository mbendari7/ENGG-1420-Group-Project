public class Booking { //booking class
    String bookingId;
    User user;
    Event event;
    BookingStatus status;

    public Booking(String bookingId, User user, Event event, BookingStatus status) { //booking contsructor
        this.bookingId = bookingId;
        this.user = user;
        this.event = event;
        this.status = status;
    }
}
