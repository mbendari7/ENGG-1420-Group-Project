// Staff.java
public class Staff extends User {

    // Constructor
    public Staff(String userId, String name, String email) {
        super(userId, name, email);
    }

    // Return correct user type
    @Override
    public String getUserType() {
        return "Staff";
    }

    // Staff can have max 5 confirmed bookings
    @Override
    public int getMaxBookings() {
        return 5;
    }
}