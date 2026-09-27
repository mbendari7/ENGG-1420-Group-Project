import javax.swing.*;
import java.awt.*;

public class UserInputForm {
    public UserInputForm() {
        JFrame frame = new JFrame("Add User");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(350, 250);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JComboBox<String> typeBox = new JComboBox<>(new String[]{"Student", "Staff", "Guest"});

        panel.add(new JLabel("User ID:"));
        panel.add(idField);
        panel.add(new JLabel("Full Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Type:"));
        panel.add(typeBox);

        JButton saveButton = new JButton("Save");
        panel.add(new JLabel(""));
        panel.add(saveButton);

        saveButton.addActionListener(e -> {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String userType = (String) typeBox.getSelectedItem();

            if (id.isEmpty() || name.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill all fields.");
                return;
            }

            User newUser;
            if (userType.equals("Student")) {
                newUser = new Student(id, name, email);
            } else if (userType.equals("Staff")) {
                newUser = new Staff(id, name, email);
            } else {
                newUser = new Guest(id, name, email);
            }

            Main.allUsers.add(newUser);

            DataSaver saver = new DataSaver();
            saver.saveSystemState(Main.allUsers, Main.bookingManager.allEvents, Main.bookingManager.allBookings);

            JOptionPane.showMessageDialog(frame, "User saved successfully.");
            frame.dispose();
        });

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}