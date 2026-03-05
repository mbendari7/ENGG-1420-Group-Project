// Seminar.java 
public class Seminar extends Event {

    String speakerName;

    public Seminar(String eventId, String title, String dateTime, String location, int capacity, String speakerName) {
        super(eventId, title, dateTime, location, capacity);
        this.speakerName = speakerName;
    }

    public String getEventType() {
        return "Seminar";
    }
}