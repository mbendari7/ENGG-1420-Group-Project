import java.util.ArrayList;

public class SystemState {
    public ArrayList<User> allUsers;
    public ArrayList<Event> allEvents;
    public ArrayList<Booking> allBookings;
    public int nextBookingId;
    
    public SystemState(ArrayList<User> allUsers, ArrayList<Event> allEvents, ArrayList<Booking> allBookings, int nextBookingId) {
        this.allUsers = allUsers;
        this.allEvents = allEvents;
        this.allBookings = allBookings;
        this.nextBookingId = nextBookingId;
    }
}
