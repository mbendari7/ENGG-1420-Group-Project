public class Guest extends User {

    // Constructor
    public Guest(String userId, String name, String email) {
        super(userId, name, email);
    }

    // Return correct user type
    @Override
    public String getUserType() {
        return "Guest";
    }

    // Guests can have max 3 confirmed bookings
    @Override
    public int getMaxBookings() {
        return 3;
    }
}