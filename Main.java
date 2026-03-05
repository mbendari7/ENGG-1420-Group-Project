
// Main.java
import javax.swing.*;
import java.awt.*;

public class Main {
    // Centralized data manager for the entire application to hold system state
    public static BookingManager bookingManager = new BookingManager();

    public static void main(String[] args) {
        // Main Navigation Menu
        JFrame mainFrame = new JFrame("Campus Event Booking System - Phase 1");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(400, 350);
        mainFrame.setLayout(new GridLayout(5, 1, 10, 10));

        JLabel titleLabel = new JLabel("Main Navigation Menu", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainFrame.add(titleLabel);

        // 1. User Management [cite: 95]
        JButton userMenuBtn = new JButton("1. User Management");
        userMenuBtn.addActionListener(e -> new UserInputForm());
        mainFrame.add(userMenuBtn);

        // 2. Event Management [cite: 102]
        JButton eventMenuBtn = new JButton("2. Event Management");
        eventMenuBtn.addActionListener(e -> new EventInputForm());
        mainFrame.add(eventMenuBtn);

        // 3. Booking Management [cite: 114]
        JButton bookingMenuBtn = new JButton("3. Booking Management");
        bookingMenuBtn
                .addActionListener(e -> JOptionPane.showMessageDialog(mainFrame, "Booking UI placeholder for Phase 1"));
        mainFrame.add(bookingMenuBtn);

        // 4. Waitlist Management [cite: 122]
        JButton waitlistMenuBtn = new JButton("4. Waitlist Management");
        waitlistMenuBtn.addActionListener(
                e -> JOptionPane.showMessageDialog(mainFrame, "Waitlist UI placeholder for Phase 1"));
        mainFrame.add(waitlistMenuBtn);

        mainFrame.setLocationRelativeTo(null); // Centers the window on your screen
        mainFrame.setVisible(true);
    }
}