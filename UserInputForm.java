
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
        String[] types = { "Student", "Staff", "Guest" }; // this just creates a list of the 3 user types.
        panel.add(new JComboBox<>(types)); // JComboBox is simply just a dropdown menu,.. takes the list and lets user
                                           // click from them.

        JButton btn = new JButton("Save User"); // clickable button at the bottom, for now it doesnt do anything tho
        panel.add(new JLabel("")); // later we will make it actually save the data of the user
        panel.add(btn);

        frame.add(panel); // this just glues the panel into the window frame
        frame.setLocationRelativeTo(null); // Centers window
        frame.setVisible(true); // ** WITHOUT this you won't see it on ur screen.
    }
}