import javax.swing.*;
import java.awt.*;

public class EventInputForm {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Phase 1: Create Event");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

        panel.add(new JLabel("Event ID:"));
        panel.add(new JTextField());

        panel.add(new JLabel("Title:"));
        panel.add(new JTextField());

        panel.add(new JLabel("Capacity:"));
        panel.add(new JTextField());

        panel.add(new JLabel("Type:"));
        String[] eTypes = {"Workshop", "Seminar", "Concert"};
        panel.add(new JComboBox<>(eTypes));

        JButton btn = new JButton("Create Event");
        panel.add(new JLabel(""));
        panel.add(btn);

        frame.add(panel);
        frame.setVisible(true);
    }
}