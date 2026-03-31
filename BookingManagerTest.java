import org.junit.Test;
import static org.junit.Assert.*;

public class BookingManagerTest {

    @Test
    public void bookingUnderCapacity_shouldBeConfirmed() {
        BookingManager manager = new BookingManager();

        Event event = new Workshop("E1", "Java Workshop", "2026-03-01T10:00", "Room 1", 2, "OOP");
        User user = new Student("U1", "Ali", "ali@email.com");

        String result = manager.createBooking(user, event);

        assertTrue(result.contains("Confirmed"));
    }

    @Test
    public void bookingWhenFull_shouldBeWaitlisted() {
        BookingManager manager = new BookingManager();

        Event event = new Workshop("E1", "Java Workshop", "2026-03-01T10:00", "Room 1", 1, "OOP");

        User u1 = new Student("U1", "Ali", "a@email.com");
        User u2 = new Student("U2", "Sara", "s@email.com");

        manager.createBooking(u1, event);
        String result = manager.createBooking(u2, event);

        assertTrue(result.contains("Waitlisted"));
    }

    @Test
    public void cancelBooking_shouldPromoteWaitlist() {
        BookingManager manager = new BookingManager();

        Event event = new Workshop("E1", "Java Workshop", "2026-03-01T10:00", "Room 1", 1, "OOP");

        User u1 = new Student("U1", "Ali", "a@email.com");
        User u2 = new Student("U2", "Sara", "s@email.com");

        String b1 = manager.createBooking(u1, event);
        manager.createBooking(u2, event);

        String bookingId = b1.split(" ")[2];

        String result = manager.cancelBooking(bookingId);

        assertTrue(result.contains("PROMOTED"));
    }

    @Test
    public void bookingLimit_shouldBlockExtraBookings() {
        BookingManager manager = new BookingManager();

        Event event = new Workshop("E1", "Java Workshop", "2026-03-01T10:00", "Room 1", 5, "OOP");

        User user = new Student("U1", "Ali", "a@email.com");

        manager.createBooking(user, event);
        manager.createBooking(user, event);
        manager.createBooking(user, event);

        String result = manager.createBooking(user, event);

        assertTrue(result.contains("ERROR"));
    }
}