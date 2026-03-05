// Event.java
public abstract class Event { // event class (made abstract for Phase 1 subclasses)
    String eventId;
    String title;
    String dateTime; // added to meet project requirements
    String location;
    int capacity;
    EventStatus status; // Using your new EventStatus enum

    public Event(String eventId, String title, String dateTime, String location, int capacity) { // event constructor
        this.eventId = eventId;
        this.title = title;
        this.dateTime = dateTime;
        this.location = location;
        this.capacity = capacity;
        this.status = EventStatus.ACTIVE; // Defaults to ACTIVE [cite: 28]
    }

    public abstract String getEventType(); // getter for event type
}