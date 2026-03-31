import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

public class Main {
    public static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<Event> events = new ArrayList<>();
    public static ArrayList<Booking> bookings = new ArrayList<>();
    public static BookingManager bookingManager;

    public static void main(String[] args) {
        DataLoader loader = new DataLoader();
        SystemState state = loader.loadSystemState();

        users = state.users;
        events = state.events;
        bookings = state.bookings;

        bookingManager = new BookingManager(users, events, bookings, state.nextBookingId);

        showMainMenu();
    }

    public static void showMainMenu() {
        JFrame frame = new JFrame("Campus Event System - Phase 2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLayout(new GridLayout(7, 1, 10, 10));

        JButton userBtn = new JButton("User Management");
        JButton eventBtn = new JButton("Event Management");
        JButton bookingBtn = new JButton("Booking Operations");
        JButton saveBtn = new JButton("SAVE SYSTEM STATE");

        userBtn.addActionListener(e -> new UserInputForm());
        eventBtn.addActionListener(e -> new EventInputForm(bookingManager));
        bookingBtn.addActionListener(e -> new BookingForm());

        saveBtn.addActionListener(e -> {
            new DataSaver().saveSystemState(users, events, bookings);
            JOptionPane.showMessageDialog(frame, "System state saved to CSV!");
        });

        frame.add(new JLabel("Main Menu", SwingConstants.CENTER));
        frame.add(userBtn);
        frame.add(eventBtn);
        frame.add(bookingBtn);
        frame.add(saveBtn);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}