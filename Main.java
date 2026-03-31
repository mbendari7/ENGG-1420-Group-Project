import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main {
    public static BookingManager bookingManager = new BookingManager();

    public static ArrayList<User> allUsers = new ArrayList<User>();

    public static void main(String[] args) {

        DataLoader loader = new DataLoader();
        SystemState loadedState = loader.loadSystemState();

    if (loadedState != null) {
        allUsers = loadedState.allUsers;
        bookingManager.allEvents = loadedState.allEvents;
        bookingManager.allBookings = loadedState.allBookings;
        bookingManager.nextBookingId = loadedState.nextBookingId;
}

       
        JFrame mainFrame = new JFrame("Campus Event Booking System - Phase 1");
        //intercept the close button to run DataSaver before exiting, basically an autosave feature
        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mainFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                DataSaver saver = new DataSaver(); // save the system state before exiting
                // Avoid wiping events/bookings if they failed to load (empty lists)
                saver.saveUsers(allUsers);
                if (bookingManager.allEvents != null && bookingManager.allEvents.size() > 0) {
                    saver.saveEvents(bookingManager.allEvents);
                }
                if (bookingManager.allBookings != null && bookingManager.allBookings.size() > 0) {
                    saver.saveBookings(bookingManager.allBookings);
                }
                System.exit(0);
            }
        });
        mainFrame.setSize(400, 350);
        mainFrame.setLayout(new GridLayout(5, 1, 10, 10));

        JLabel titleLabel = new JLabel("Main Navigation Menu", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainFrame.add(titleLabel);

        JButton userMenuBtn = new JButton("1. User Management");
        userMenuBtn.addActionListener(e -> new UserInputForm());
        mainFrame.add(userMenuBtn);

        JButton eventMenuBtn = new JButton("2. Event Management");
        eventMenuBtn.addActionListener(e -> new EventInputForm(bookingManager));
        mainFrame.add(eventMenuBtn);

        JButton bookingMenuBtn = new JButton("3. Booking Management");
        bookingMenuBtn.addActionListener(e -> new BookingForm()); // Links to your BookingForm!
        mainFrame.add(bookingMenuBtn);

        JButton waitlistMenuBtn = new JButton("4. Waitlist Management");
        waitlistMenuBtn.addActionListener(e -> new WaitlistForm()); // Links to the new WaitlistForm!
        mainFrame.add(waitlistMenuBtn);

        mainFrame.setLocationRelativeTo(null); // Centers the window on your screen
        mainFrame.setVisible(true);
    }
}
