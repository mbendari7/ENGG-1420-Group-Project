import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class WaitlistForm {
    public WaitlistForm() {
        JFrame frame = new JFrame("Waitlist Management");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(450, 400);
        frame.setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new FlowLayout());
        JTextField eventIdField = new JTextField(10);
        JButton viewBtn = new JButton("View Waitlist");
        topPanel.add(new JLabel("Event ID:"));
        topPanel.add(eventIdField);
        topPanel.add(viewBtn);

        JTextArea displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(displayArea);

        viewBtn.addActionListener(e -> {
            String id = eventIdField.getText().trim();
            Event event = Main.bookingManager.getEventById(id);

            if (event != null) {
                displayArea.setText("Waitlist for Event: " + event.getTitle() + "\n");
                displayArea.append("==============================\n");

                ArrayList<User> list = event.getWaitlist();
                if (list == null || list.isEmpty()) {
                    displayArea.append("No users currently on the waitlist.");
                } else {
                    for (int i = 0; i < list.size(); i++) {
                        User u = list.get(i);
                        displayArea.append((i + 1) + ". " + u.getName() + " (" + u.getEmail() + ")\n");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Event ID not found.");
            }
        });

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}