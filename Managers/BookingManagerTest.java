import org.junit.Test;
import static org.junit.Assert.*;

public class BookingManagerTest {

    // checks that a booking is confirmed if there is still space
    @Test
    public void bookingUnderCapacity_shouldBeConfirmed() {
        BookingManager manager = new BookingManager();

        // event has capacity for 2 people
        Event event = new Workshop("E1", "Java Workshop", "2026-03-01T10:00", "Room 1", 2, "OOP");
        User user = new Student("U1", "Ali", "ali@email.com");

        String result = manager.createBooking(user, event);

        // should be confirmed since event is not full
        assertTrue(result.contains("Confirmed"));
    }

    // checks that booking goes to waitlist when event is full
    @Test
    public void bookingWhenFull_shouldBeWaitlisted() {
        BookingManager manager = new BookingManager();

        // capacity is only 1
        Event event = new Workshop("E1", "Java Workshop", "2026-03-01T10:00", "Room 1", 1, "OOP");

        User u1 = new Student("U1", "Ali", "a@email.com");
        User u2 = new Student("U2", "Sara", "s@email.com");

        manager.createBooking(u1, event); // fills the only spot
        String result = manager.createBooking(u2, event);

        // second user should be waitlisted
        assertTrue(result.contains("Waitlisted"));
    }

    // checks that cancelling a confirmed booking promotes someone from the waitlist
    @Test
    public void cancelBooking_shouldPromoteWaitlist() {
        BookingManager manager = new BookingManager();

        Event event = new Workshop("E1", "Java Workshop", "2026-03-01T10:00", "Room 1", 1, "OOP");

        User u1 = new Student("U1", "Ali", "a@email.com");
        User u2 = new Student("U2", "Sara", "s@email.com");

        String b1 = manager.createBooking(u1, event); // confirmed
        manager.createBooking(u2, event); // waitlisted

        // extract booking ID from returned string
        String bookingId = b1.split(" ")[2];

        String result = manager.cancelBooking(bookingId);

        // after cancellation, waitlisted user should be promoted
        assertTrue(result.contains("PROMOTED"));
    }

    // checks that booking limit is enforced for a user
    @Test
    public void bookingLimit_shouldBlockExtraBookings() {
        BookingManager manager = new BookingManager();

        Event event = new Workshop("E1", "Java Workshop", "2026-03-01T10:00", "Room 1", 5, "OOP");

        User user = new Student("U1", "Ali", "a@email.com");

        // student reaches max allowed bookings
        manager.createBooking(user, event);
        manager.createBooking(user, event);
        manager.createBooking(user, event);

        String result = manager.createBooking(user, event);

        // should return an error after limit is reached
        assertTrue(result.contains("ERROR"));
    }
}