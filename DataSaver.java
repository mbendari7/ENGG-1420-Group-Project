import java.io.FileWriter;
import java.util.ArrayList;

public class DataSaver {

    public void saveSystemState(ArrayList<User> allUsers, ArrayList<Event> allEvents, ArrayList<Booking> allBookings) {
        saveUsers(allUsers);
        saveEvents(allEvents);
        saveBookings(allBookings);
        System.out.println("System saved."); // this prints a message so we know it finished, we did this bcz it helps with testing
    }

    public void saveUsers(ArrayList<User> users) {
        try {
            FileWriter usersWriter = new FileWriter("saved_users.csv");
            usersWriter.write("userId,name,email,userType\n");

            for (int i = 0; i < users.size(); i++) {
                User currentUser = users.get(i);

                String userIdText = currentUser.userId + "";
                String userNameText = currentUser.name + "";
                String userEmailText = currentUser.email + "";
                String userTypeText = currentUser.getUserType() + "";

                String oneUserLine = userIdText + "," + userNameText + "," + userEmailText + "," + userTypeText;
                usersWriter.write(oneUserLine + "\n");
            }
            usersWriter.close();
        } catch (Exception e) {
            System.out.println("Error saving users."); // this prints an error, we did this bcz try-catch requires us to handle file crashes
        }
    }

    public void saveEvents(ArrayList<Event> events) {
        try {
            FileWriter eventsWriter = new FileWriter("saved_events.csv");
            eventsWriter.write("eventId,title,dateTime,location,capacity,status,eventType,topic,speakerName,ageRestriction\n"); // this writes the csv header for the events file

            for (int i = 0; i < events.size(); i++) {
                Event currentEvent = events.get(i);
                String type = currentEvent.getEventType();

                String lineStart = currentEvent.eventId + "," + currentEvent.title + "," + currentEvent.dateTime + "," + currentEvent.location + ",";
                lineStart = lineStart + currentEvent.capacity + "," + currentEvent.status + "," + type + ",";

                String extraPart = "";

                // we cast Event to a subclass since generic events dont have topics or speakers
                if (type.equals("Workshop")) {
                    Workshop workshopEvent = (Workshop) currentEvent;
                    extraPart = workshopEvent.topic + ",,";
                } else if (type.equals("Seminar")) {
                    Seminar seminarEvent = (Seminar) currentEvent;
                    extraPart = "," + seminarEvent.speakerName + ",";
                } else if (type.equals("Concert")) {
                    Concert concertEvent = (Concert) currentEvent;
                    extraPart = ",," + concertEvent.ageRestriction;
                } else {
                    extraPart = ",,";
                }

                String fullLine = lineStart + extraPart;
                eventsWriter.write(fullLine + "\n"); // this writes the row to the file
            }
            eventsWriter.close();
        } catch (Exception e) {
            System.out.println("Error saving events."); // this catches errors, we did this because file writing can fail if the file is locked
        }
    }

    public void saveBookings(ArrayList<Booking> bookings) {
        try {
            FileWriter bookingsWriter = new FileWriter("saved_bookings.csv");
            bookingsWriter.write("bookingId,userId,eventId,createdAt,bookingStatus\n");

            for (int i = 0; i < bookings.size(); i++) {
                Booking currentBooking = bookings.get(i);

                String bookingIdText = currentBooking.bookingId + "";
                String userIdText = currentBooking.user.userId + "";
                String eventIdText = currentBooking.event.eventId + "";
                String createdAtText = currentBooking.createdAt + "";
                String statusText = currentBooking.status + "";

                String bookingLine = bookingIdText + "," + userIdText + "," + eventIdText + "," + createdAtText + "," + statusText;
                bookingsWriter.write(bookingLine + "\n");
            }
            bookingsWriter.close();
        } catch (Exception e) {
            System.out.println("Error saving bookings."); // this handles the exception, we did this bcz the program will crash if we dont have a catch
        }
    }


}

