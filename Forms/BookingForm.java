import javax.swing.*;
import java.awt.*;

public class BookingForm {
    public BookingForm() {
        // 1. Declare the frame first so it can be used in pop-ups later
        JFrame frame = new JFrame("Booking Management");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(4, 2, 10, 10));

        // 2. Declare input fields
        JTextField userIdField = new JTextField();
        JTextField eventIdField = new JTextField();
        JButton bookBtn = new JButton("Confirm Booking");

        // 3. Add components to the frame
        frame.add(new JLabel("User ID:"));
        frame.add(userIdField);
        frame.add(new JLabel("Event ID:"));
        frame.add(eventIdField);
        frame.add(new JLabel(""));
        frame.add(bookBtn);

        // 4. Action Listener Logic
        bookBtn.addActionListener(e -> {
            String uId = userIdField.getText().trim();
            String eId = eventIdField.getText().trim();

            // Search for the User
            User selectedUser = null;
            for (User u : Main.allUsers) {
                if (u.getUserId().equals(uId)) {
                    selectedUser = u;
                    break;
                }
            }

            // Search for the Event
            Event selectedEvent = Main.bookingManager.getEventById(eId);

            if (selectedUser != null && selectedEvent != null) {
                // Logic for booking vs waitlist
                boolean success = Main.bookingManager.createBooking(selectedUser, selectedEvent);

                if (success) {
                    JOptionPane.showMessageDialog(frame, "Booking successful!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Event full. Added to Waitlist.");
                }
                frame.dispose();
            } else {
                // This 'frame' reference is why your code was red
                JOptionPane.showMessageDialog(frame, "Error: User or Event ID not found.");
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}