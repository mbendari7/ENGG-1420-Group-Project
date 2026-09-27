// Workshop.java
public class Workshop extends Event {

    String topic;

    public Workshop(String eventId, String title, String dateTime, String location, int capacity, String topic) {
        super(eventId, title, dateTime, location, capacity); // Added dateTime
        this.topic = topic;
    }

    public String getEventType() {
        return "Workshop";
    }
}