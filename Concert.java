// Concert.java
public class Concert extends Event {

    String ageRestriction;

    public Concert(String eventId, String title, String dateTime, String location, int capacity,
            String ageRestriction) {
        super(eventId, title, dateTime, location, capacity); // Added dateTime
        this.ageRestriction = ageRestriction;
    }

    public String getEventType() {
        return "Concert";
    }
}