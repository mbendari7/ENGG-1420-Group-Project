public class User {
    String userId;
    String name;
    String email;

    public User(String userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    public String getUserType() {
        return "User";
    }

    public int getMaxBookings() {
        return 0;
    }
}
