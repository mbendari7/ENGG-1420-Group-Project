public class Concert extends Event {

    String ageRestriction;

    public Concert(String eventId, String title, String location, int capacity, String ageRestriction) {
        super(eventId, title, location, capacity);
        this.ageRestriction = ageRestriction;
    }

    public String getEventType() {
        return "Concert";
    }
}