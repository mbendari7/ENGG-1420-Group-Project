import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class WaitlistForm {
    public WaitlistForm() {
        // Create the window for viewing the waitlist
        JFrame frame = new JFrame("Waitlist Management");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        frame.setSize(450, 350); //we can modify the size of the window later

        // Main panel 
        JPanel mainPanel = new JPanel(); //this is the main panel for the waitlist form
        mainPanel.setLayout(new BorderLayout(10, 10));

        // Top area for input
        JPanel topPanel = new JPanel();

        // 1. Which event are we looking at?
        topPanel.add(new JLabel("Event ID:"));
        JTextField eventIdField = new JTextField(10);
        topPanel.add(eventIdField);

        // View button
        JButton viewButton = new JButton("View Waitlist");
        topPanel.add(viewButton); //this adds the view button to the top panel

        // Where to show the results
        JTextArea displayArea = new JTextArea();
        displayArea.setEditable(false); //this stops users from typing in it
        JScrollPane scrollPane = new JScrollPane(displayArea);

        // What happens when view is clicked
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String targetEventId = eventIdField.getText(); //this gets the event id from the text field

                // Basic check for empty fields
                if (targetEventId.trim().equals("")) {
                    JOptionPane.showMessageDialog(frame, "Please enter an Event ID."); //this is a popup window
                    return;
                }

                ArrayList<Booking> waitlist = new ArrayList<Booking>();

                // Loop to find the waitlisted bookings
                for (int i = 0; i < Main.bookingManager.allBookings.size(); i++) {
                    Booking b = Main.bookingManager.allBookings.get(i); //this gets the booking from the list of bookings
                    if (b.event.eventId.equals(targetEventId)) { //this checks if the event id is the same as the event id in the text field
                        if (b.status == BookingStatus.WAITLISTED) {
                            waitlist.add(b); //this adds them to our list
                        }
                    }
                }

                // Check if anyone is actually waitlisted
                if (waitlist.size() == 0) {
                    displayArea.setText("No one is on the waitlist for Event " + targetEventId);
                    return;
                }

                // Sort the waitlist by time (First come first served)
                for (int i = 0; i < waitlist.size() - 1; i++) { //this sorts the waitlist by time
                    for (int j = 0; j < waitlist.size() - i - 1; j++) {
                        Booking b1 = waitlist.get(j); //this gets the booking from the list of bookings
                        Booking b2 = waitlist.get(j + 1);

                        // check the dates to see who was first
                        if (b1.createdAt.compareTo(b2.createdAt) > 0) {
                            // swap them around
                            Booking temp = waitlist.get(j);
                            waitlist.set(j, waitlist.get(j + 1)); //this swaps the bookings
                            waitlist.set(j + 1, temp);
                        }
                    }
                }

                // Print the results to the screen
                String text = "Waitlist for Event: " + targetEventId + "\n\n";
                for (int i = 0; i < waitlist.size(); i++) {
                    Booking b = waitlist.get(i);
                    text = text + (i + 1) + ". User: " + b.user.name + " (" + b.user.userId + ")\n";
                    text = text + "   Booking ID: " + b.bookingId + " | Time: " + b.createdAt + "\n\n";
                }

                displayArea.setText(text); //this puts the text into the big text area
            }
        });

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Final window setup
        frame.add(mainPanel); //this just glues the panel into the window frame
        frame.setLocationRelativeTo(null); // Centers window
        frame.setVisible(true); // ** WITHOUT this you won't see it on ur screen.
    }
}