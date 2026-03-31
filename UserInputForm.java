
// UserInputForm.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInputForm {
    public UserInputForm() { // Changed to constructor
        JFrame frame = new JFrame("Phase 1: Add User");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // FIXED: prevents closing entire app
        frame.setSize(350, 250); // we can modify these later (width & height of window in pxls)

        // GridLayout just organizes the items in a 5x2 grid. the 10, 10 adds gaps
        // between boxes (we can modify)
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        // Jlabel is text that user CANNOT change... JTextField is the empty white box
        // where user types their info.
        // repeat Jlabel & JTextField for ID, name, and email:
        panel.add(new JLabel("User ID:"));
        panel.add(new JTextField());

        panel.add(new JLabel("Full Name:"));
        panel.add(new JTextField());

        panel.add(new JLabel("Email:"));
        panel.add(new JTextField());

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
        frame.add(panel); // this just glues the panel into the window frame
        frame.setLocationRelativeTo(null); // Centers window
        frame.setVisible(true); // ** WITHOUT this you won't see it on ur screen.
    }
}