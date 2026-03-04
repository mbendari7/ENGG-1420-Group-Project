public class Event { //event class
    String eventId;
    String title;
    String location;
    int capacity;

    public Event(String eventId, String title, String location, int capacity) { //event constructor
        this.eventId = eventId;
        this.title = title;
        this.location = location;
        this.capacity = capacity;
    }

    public String getEventType() { //getter for event type
        return "Event";
    }
}
