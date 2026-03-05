import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main {

    public static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<Event> events = new ArrayList<>();
    public static BookingManager bookingManager = new BookingManager();

    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Campus Event Booking System - Phase 1");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(400, 350);
        mainFrame.setLayout(new GridLayout(5, 1, 10, 10));

        JLabel titleLabel = new JLabel("Main Navigation Menu", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainFrame.add(titleLabel);

        JButton userMenuBtn = new JButton("1. User Management");
        userMenuBtn.addActionListener(e -> new UserInputForm());
        mainFrame.add(userMenuBtn);

        JButton eventMenuBtn = new JButton("2. Event Management");
        eventMenuBtn.addActionListener(e -> new EventInputForm());
        mainFrame.add(eventMenuBtn);

        JButton bookingMenuBtn = new JButton("3. Booking Management");
        bookingMenuBtn.addActionListener(e -> new BookingForm());
        mainFrame.add(bookingMenuBtn);

        JButton waitlistMenuBtn = new JButton("4. Waitlist Management");
        waitlistMenuBtn.addActionListener(e -> new WaitlistForm());
        mainFrame.add(waitlistMenuBtn);

        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }
}