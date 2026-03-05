import javax.swing.*;
import java.awt.*;

public class EventInputForm {
    public EventInputForm() {
        JFrame frame = new JFrame("Phase 1: Create Event");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(450, 400); // Made slightly taller to fit the new fields

        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10)); // Updated to 8 rows

        // 1. Create variables for all the text boxes so we can read them
        JTextField idField = new JTextField();
        JTextField titleField = new JTextField();
        JTextField dateTimeField = new JTextField();
        JTextField locationField = new JTextField();
        JTextField capacityField = new JTextField();
        JTextField specificField = new JTextField(); // Used for Topic / Speaker / Age Restriction

        String[] eTypes = { "Workshop", "Seminar", "Concert" };
        JComboBox<String> typeBox = new JComboBox<>(eTypes);

        // 2. Add them to the panel
        panel.add(new JLabel("Event ID:"));
        panel.add(idField);

        panel.add(new JLabel("Title:"));
        panel.add(titleField);

        panel.add(new JLabel("Date/Time (e.g. 2026-03-10):"));
        panel.add(dateTimeField);

        panel.add(new JLabel("Location:"));
        panel.add(locationField);

        panel.add(new JLabel("Capacity (Number):"));
        panel.add(capacityField);

        panel.add(new JLabel("Type:"));
        panel.add(typeBox);

        panel.add(new JLabel("Specific Info (Topic/Speaker/Age):"));
        panel.add(specificField);

        JButton btn = new JButton("Create Event");

        // --- 3. THE BACKEND LOGIC TO SAVE THE EVENT ---
        btn.addActionListener(e -> {
            try {
                // Read what the user typed
                String id = idField.getText();
                String title = titleField.getText();
                String dateTime = dateTimeField.getText();
                String location = locationField.getText();
                int capacity = Integer.parseInt(capacityField.getText()); // Converts text to a number
                String type = (String) typeBox.getSelectedItem();
                String specificInfo = specificField.getText();

                // Create the correct Event object based on the dropdown
                Event newEvent = null;
                if (type.equals("Workshop")) {
                    newEvent = new Workshop(id, title, dateTime, location, capacity, specificInfo);
                } else if (type.equals("Seminar")) {
                    newEvent = new Seminar(id, title, dateTime, location, capacity, specificInfo);
                } else if (type.equals("Concert")) {
                    newEvent = new Concert(id, title, dateTime, location, capacity, specificInfo);
                }

                // Save it to Main's memory list
                Main.events.add(newEvent);

                // Show success popup and close window
                JOptionPane.showMessageDialog(frame, type + " created successfully!");
                frame.dispose();

            } catch (NumberFormatException ex) {
                // Prevents a crash if they type a word instead of a number for capacity
                JOptionPane.showMessageDialog(frame, "Error: Capacity must be a valid number!");
            }
        });

        panel.add(new JLabel("")); // Spacer
        panel.add(btn);

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}