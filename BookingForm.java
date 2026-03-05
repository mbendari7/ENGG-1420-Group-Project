import javax.swing.*;
import java.awt.*;

public class BookingForm {
    public BookingForm() { // Changed from main() to a constructor
        JFrame frame = new JFrame("Phase 1: Book Event");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // FIXED: Prevents app crash
        frame.setSize(350, 200);

        // A 4-row, 2-column grid layout
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        // 1. Who is booking?
        panel.add(new JLabel("User ID:"));
        panel.add(new JTextField());

        // 2. What are they booking?
        panel.add(new JLabel("Event ID:"));
        panel.add(new JTextField());

        // 3. What is the status?
        panel.add(new JLabel("Status:"));
        String[] statuses = { "Confirmed", "Waitlisted", "Cancelled" };
        panel.add(new JComboBox<>(statuses));

        // 4. The Action Button
        JButton bookBtn = new JButton("Confirm Booking");
        panel.add(new JLabel("")); // Empty spacer to push button to the right
        panel.add(bookBtn);

        frame.add(panel);
        frame.setLocationRelativeTo(null); // Centers the window on your screen
        frame.setVisible(true);
    }
}