public class User { //user class
    String userId;
    String name;
    String email;

    public User(String userId, String name, String email) { //user constructor
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    public String getUserType() { //getter for user type
        return "User";
    }

    public int getMaxBookings() { //getter for max bookings
        return 0;
    }
}
