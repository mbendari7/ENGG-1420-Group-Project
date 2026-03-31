import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookingForm {
    public BookingForm() {
        // Create the window for booking an event
        JFrame frame = new JFrame("Book Event");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //FIXED: prevents closing entire app
        frame.setSize(360, 260); //we can modify the size of the window later

        // Main panel with rows and columns
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 8, 8));

        // Input fields
        JTextField userIdField = new JTextField(); //this is the field for the user id to book
        JTextField eventIdField = new JTextField();  //this is the field for the event id to book
        JTextField cancelIdField = new JTextField(); //this is the field for the booking id to cancel

        // Add labels and fields to panel for booking
        panel.add(new JLabel("User ID:")); //this is the label for the user id to book
        panel.add(userIdField);
        panel.add(new JLabel("Event ID:")); //this is the label for the event id to book
        panel.add(eventIdField);

        // Book button
        JButton bookButton = new JButton("Book Event");
        panel.add(new JLabel(""));
        panel.add(bookButton); //this adds the book button to the panel

        // Add labels and fields to panel for canceling
        panel.add(new JLabel("Booking ID (to cancel):"));
        panel.add(cancelIdField);

        // Cancel button
        JButton cancelButton = new JButton("Cancel Booking");
        panel.add(new JLabel(""));
        panel.add(cancelButton); //this adds the cancel button to the panel

        // What happens when book is clicked
        bookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String uId = userIdField.getText(); //this gets the user id from the text field
                String eId = eventIdField.getText(); //this gets the event id from the text field

                // Basic check for empty fields
                if (uId.trim().equals("") || eId.trim().equals("")) {
                    JOptionPane.showMessageDialog(frame, "Please fill all fields.");
                    return;
                }

                User foundUser = null;
                // Loop to find the user
                for (int i = 0; i < Main.allUsers.size(); i++) {
                    if (Main.allUsers.get(i).userId.equals(uId)) { //this checks if the user id is the same as the user id in the text field
                        foundUser = Main.allUsers.get(i); //this gets the user from the list of users
                    }
                }

                Event foundEvent = null;
                // Loop to find the event
                for (int i = 0; i < Main.bookingManager.allEvents.size(); i++) {
                    if (Main.bookingManager.allEvents.get(i).getEventId().equals(eId)) { //this checks if the event id is the same as the event id in the text field
                        foundEvent = Main.bookingManager.allEvents.get(i); //this gets the event from the list of events
                    }
                }

                // Check if user and event exist
                if (foundUser == null || foundEvent == null) {
                    JOptionPane.showMessageDialog(frame, "User or Event not found."); //this is a popup window
                    return;
                }

                // Create the booking
                String message = Main.bookingManager.createBooking(foundUser, foundEvent); //this makes the booking
                JOptionPane.showMessageDialog(frame, message); //this shows if it worked or waitlisted

                // Save bookings to file
                DataSaver saver = new DataSaver();
                saver.saveBookings(Main.bookingManager.allBookings); //this saves bookings to the file
            }
        });

        // What happens when cancel is clicked
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String bId = cancelIdField.getText(); //this gets the booking id from the text field

                // Basic check for empty fields
                if (bId.trim().equals("")) {
                    JOptionPane.showMessageDialog(frame, "Please enter a Booking ID.");
                    return;
                }

                // Cancel the booking
                String message = Main.bookingManager.cancelBooking(bId); //this cancels it and updates waitlist
                JOptionPane.showMessageDialog(frame, message); //this is a popup window that appears

                // Save bookings to file
                DataSaver saver = new DataSaver();
                saver.saveBookings(Main.bookingManager.allBookings); //this saves bookings to the file
            }
        });

        // Final window setup
        frame.add(panel); //this just glues the panel into the window frame
        frame.setLocationRelativeTo(null); // Centers window
        frame.setVisible(true); // ** WITHOUT this you won't see it on ur screen.
    }
}