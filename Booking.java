public class Booking {
    String bookingId;
    User user;
    Event event;
    String status;

    public Booking(String bookingId, User user, Event event, String status) {
        this.bookingId = bookingId;
        this.user = user;
        this.event = event;
        this.status = status;
    }
}
