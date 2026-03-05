import javax.swing.*;
import java.awt.*;

public class UserInputForm {
    public UserInputForm() {
        JFrame frame = new JFrame("Phase 1: Add User");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(350, 250);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        // Create variables for the text boxes so we can read them later
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        String[] types = { "Student", "Staff", "Guest" };
        JComboBox<String> typeBox = new JComboBox<>(types);

        panel.add(new JLabel("User ID:"));
        panel.add(idField);

        panel.add(new JLabel("Full Name:"));
        panel.add(nameField);

        panel.add(new JLabel("Email:"));
        panel.add(emailField);

        panel.add(new JLabel("Type:"));
        panel.add(typeBox);

        JButton btn = new JButton("Save User");

        // --- THE BACKEND LOGIC ---
        btn.addActionListener(e -> {
            String id = idField.getText();
            String name = nameField.getText();
            String email = emailField.getText();
            String type = (String) typeBox.getSelectedItem();

            // Create the correct object based on the dropdown
            User newUser = null;
            if (type.equals("Student"))
                newUser = new Student(id, name, email);
            else if (type.equals("Staff"))
                newUser = new Staff(id, name, email);
            else if (type.equals("Guest"))
                newUser = new Guest(id, name, email);

            // Save it to Main's memory
            Main.users.add(newUser);

            // Show a success popup and close the window
            JOptionPane.showMessageDialog(frame, type + " saved successfully!");
            frame.dispose();
        });

        panel.add(new JLabel(""));
        panel.add(btn);

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}