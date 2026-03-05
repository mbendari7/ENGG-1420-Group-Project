public class Workshop extends Event {

    String topic;

    public Workshop(String eventId, String title, String location, int capacity, String topic) {
        super(eventId, title, location, capacity);
        this.topic = topic;
    }

    public String getEventType() {
        return "Workshop";
    }
}