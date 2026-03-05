// Guest.java
public class Guest extends User { // guest class

    // Constructor
    public Guest(String userId, String name, String email) { // guest constructor
        super(userId, name, email);
    }

    // Return correct user type
    @Override
    public String getUserType() { // getter for user type
        return "Guest";
    }

    // Guests can have max 1 confirmed booking [cite: 66]
    @Override
    public int getMaxBookings() { // getter for max bookings
        return 1; // changed this value from 3 to 1 bcz guests can only have a max of 1 confirmed
                  // booking
    }
}