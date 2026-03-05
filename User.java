// User.java (Abstract Base Class)
public abstract class User {
    String userId;
    String name;
    String email;

    public User(String userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    public abstract String getUserType();

    public abstract int getMaxBookings();
}