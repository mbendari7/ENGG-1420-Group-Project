import javax.swing.*;
import java.awt.*;

public class BookingForm {
    public BookingForm() {
        JFrame frame = new JFrame("Phase 1: Book & Cancel Events");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300); // Made slightly taller for the cancel section

        // 6 rows to fit both Book and Cancel features
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

        // --- BOOKING SECTION ---
        JTextField userIdField = new JTextField();
        JTextField eventIdField = new JTextField();

        panel.add(new JLabel("User ID (to book):"));
        panel.add(userIdField);

        panel.add(new JLabel("Event ID (to book):"));
        panel.add(eventIdField);

        JButton bookBtn = new JButton("Confirm Booking");

        bookBtn.addActionListener(e -> {
            String targetUserId = userIdField.getText();
            String targetEventId = eventIdField.getText();

            User foundUser = null;
            for (User u : Main.users) {
                if (u.userId.equals(targetUserId))
                    foundUser = u;
            }

            Event foundEvent = null;
            for (Event ev : Main.events) {
                if (ev.eventId.equals(targetEventId))
                    foundEvent = ev;
            }

            if (foundUser != null && foundEvent != null) {
                String resultMessage = Main.bookingManager.createBooking(foundUser, foundEvent);
                JOptionPane.showMessageDialog(frame, resultMessage);
            } else {
                JOptionPane.showMessageDialog(frame, "Error: User or Event not found in system.");
            }
        });

        panel.add(new JLabel(""));
        panel.add(bookBtn);

        // --- CANCELLATION SECTION ---
        // Empty labels to create a visual gap
        panel.add(new JLabel("--------------------"));
        panel.add(new JLabel("--------------------"));

        JTextField cancelBookingIdField = new JTextField();

        panel.add(new JLabel("Booking ID (to cancel):"));
        panel.add(cancelBookingIdField);

        JButton cancelBtn = new JButton("Cancel Booking");

        // THE CANCEL BACKEND LOGIC
        cancelBtn.addActionListener(e -> {
            String targetBookingId = cancelBookingIdField.getText();

            // Your BookingManager already handles finding it, canceling it,
            // and returning a string if someone was promoted!
            String resultMessage = Main.bookingManager.cancelBooking(targetBookingId);

            // Show the result (this satisfies the "Promotion Notification" requirement)
            JOptionPane.showMessageDialog(frame, resultMessage);
        });

        panel.add(new JLabel(""));
        panel.add(cancelBtn);

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}