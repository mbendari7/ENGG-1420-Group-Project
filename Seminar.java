public class Seminar extends Event {

    String speakerName;

    public Seminar(String eventId, String title, String location, int capacity, String speakerName) {
        super(eventId, title, location, capacity);
        this.speakerName = speakerName;
    }

    public String getEventType() {
        return "Seminar";
    }
}