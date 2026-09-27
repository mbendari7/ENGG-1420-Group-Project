import java.util.ArrayList;

public abstract class Event {
    private String eventId;
    private String title;
    private String dateTime;
    private String location;
    private int capacity;
    private ArrayList<User> attendees = new ArrayList<>();
    private ArrayList<User> waitlist = new ArrayList<>();

    public Event(String eventId, String title, String dateTime, String location, int capacity) {
        this.eventId = eventId;
        this.title = title;
        this.dateTime = dateTime;
        this.location = location;
        this.capacity = capacity;
    }

    public String getEventId() { return eventId; }
    public String getTitle() { return title; }
    public int getCapacity() { return capacity; }
    public ArrayList<User> getAttendees() { return attendees; }
    public ArrayList<User> getWaitlist() { return waitlist; }

    public abstract String getEventType();
}