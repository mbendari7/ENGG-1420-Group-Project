import javax.swing.*;
import java.awt.*;

public class WaitlistForm {
    public WaitlistForm() {
        JFrame frame = new JFrame("Phase 1: Waitlist Management");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 200);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        JTextField eventIdField = new JTextField();

        panel.add(new JLabel("Event ID:"));
        panel.add(eventIdField);

        JButton viewWaitlistBtn = new JButton("View Waitlist");

        // --- THE BACKEND LOGIC ---
        viewWaitlistBtn.addActionListener(e -> {
            String targetEventId = eventIdField.getText();
            boolean eventExists = false;

            // 1. Verify the event actually exists in our memory
            for (Event ev : Main.events) {
                if (ev.eventId.equals(targetEventId)) {
                    eventExists = true;
                    break;
                }
            }

            if (!eventExists) {
                JOptionPane.showMessageDialog(frame, "Error: Event ID not found in system.");
                return; // Stop the code here if the event doesn't exist
            }

            // 2. Search all bookings for this event's waitlist
            // We use a StringBuilder to cleanly format a multi-line message
            StringBuilder waitlistInfo = new StringBuilder("Waitlist for Event " + targetEventId + ":\n\n");
            int position = 1;

            // Looping through allBookings automatically preserves the chronological order
            for (Booking b : Main.bookingManager.allBookings) {
                if (b.event.eventId.equals(targetEventId) && b.status == BookingStatus.WAITLISTED) {
                    waitlistInfo.append(position).append(". User: ").append(b.user.name)
                            .append(" (ID: ").append(b.user.userId).append(")\n")
                            .append("   Time: ").append(b.createdAt).append("\n\n");
                    position++;
                }
            }

            if (position == 1) {
                waitlistInfo.append("The waitlist is currently empty.");
            }

            // 3. Show the results in a popup
            JOptionPane.showMessageDialog(frame, waitlistInfo.toString());
        });

        panel.add(new JLabel("")); // Spacer
        panel.add(viewWaitlistBtn);

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}