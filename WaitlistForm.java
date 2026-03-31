import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class WaitlistForm {
    public WaitlistForm() {
        JFrame frame = new JFrame("Phase 1: Waitlist Management");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Prevents app from crashing when closed
        frame.setSize(400, 200);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        panel.add(new JLabel("Event ID:"));
        panel.add(new JTextField());

        JButton viewWaitlistBtn = new JButton("View Waitlist");
        panel.add(new JLabel("")); // Empty spacer to push button to the right
        panel.add(viewWaitlistBtn);

        frame.add(panel);
        frame.setLocationRelativeTo(null); // Centers the window
        frame.setVisible(true);
    }
}