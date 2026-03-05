// Student.java
public class Student extends User {

    // Constructor
    public Student(String userId, String name, String email) {
        super(userId, name, email);
    }

    // Return correct user type
    @Override
    public String getUserType() {
        return "Student";
    }

    // Students can have max 3 confirmed bookings [cite: 64]
    @Override
    public int getMaxBookings() {
        return 3;
    }
}