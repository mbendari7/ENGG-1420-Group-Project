
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInputForm {
    public UserInputForm() {
        // Create the window for adding a user
        JFrame frame = new JFrame("Add User");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //FIXED: prevents closing entire app
        frame.setSize(360, 240); //we can modify the size of the window later

        // Main panel with rows and columns
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 8, 8));

        // Input fields
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();

        // Dropdown for user type
        JComboBox<String> typeBox = new JComboBox<String>();
        typeBox.addItem("Student");
        typeBox.addItem("Staff");
        typeBox.addItem("Guest");

        // Add labels and fields to panel
        panel.add(new JLabel("User ID:"));
        panel.add(idField);
        panel.add(new JLabel("Full Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Type:"));
        panel.add(typeBox);

        // Save button
        JButton saveButton = new JButton("Save");
        panel.add(new JLabel(""));
        panel.add(saveButton);

        // What happens when save is clicked
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String name = nameField.getText();
                String email = emailField.getText();
                String userType = typeBox.getSelectedItem().toString();

                // Basic check for empty fields
                if (id.trim().equals("") || name.trim().equals("") || email.trim().equals("")) {
                    JOptionPane.showMessageDialog(frame, "Please fill all fields.");
                    return;
                }

                // Create the correct user object based on selected type
                User newUser;
                if (userType.equals("Student")) {
                    newUser = new Student(id, name, email);
                } else if (userType.equals("Staff")) {
                    newUser = new Staff(id, name, email);
                } else {
                    newUser = new Guest(id, name, email);
                }

                // Add user to global list
                Main.allUsers.add(newUser);

                // Save users, events, and bookings to file
                DataSaver saver = new DataSaver();
                saver.saveSystemState(Main.allUsers, Main.bookingManager.allEvents, Main.bookingManager.allBookings);

                JOptionPane.showMessageDialog(frame, "User saved.");
                frame.dispose();
            }
        });

        // Final window setup
        frame.add(panel); //this just glues the panel into the window frame
        frame.setLocationRelativeTo(null); // Centers window
        frame.setVisible(true); // ** WITHOUT this you won't see it on ur screen.
    }
}