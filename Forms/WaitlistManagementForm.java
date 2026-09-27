import javax.swing.*;
import java.awt.*;

public class WaitlistManagementForm {
    public WaitlistManagementForm() {
        JFrame frame = new JFrame("Phase 1: Waitlist Management");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 200);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        panel.add(new JLabel("Event ID:"));
        panel.add(new JTextField());

        JButton viewWaitlistBtn = new JButton("View Waitlist");
        panel.add(new JLabel(""));
        panel.add(viewWaitlistBtn);

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}