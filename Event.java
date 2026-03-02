public class Event {
    String eventId;
    String title;
    String location;
    int capacity;

    public Event(String eventId, String title, String location, int capacity) {
        this.eventId = eventId;
        this.title = title;
        this.location = location;
        this.capacity = capacity;
    }

    public String getEventType() {
        return "Event";
    }
}
