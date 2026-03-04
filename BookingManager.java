import java.util.ArrayList;

public class BookingManager {
    
    ArrayList<Booking> allBookings = new ArrayList<Booking>();
    int nextBookingId = 1;

    public String createBooking(User user, Event event) { //create booking method
        
        // count user bookings
        int userCount = 0;
        for (int i = 0; i < allBookings.size(); i++) { //loop through all bookings
            if (allBookings.get(i).user.userId.equals(user.userId)) {
                if (allBookings.get(i).status == BookingStatus.CONFIRMED) { //if the booking is confirmed
                    userCount++;
                }
            }
        }
        
        // DO NOT CHANGE THIS
        // we need the polymorphism here for grades
        if (userCount >= user.getMaxBookings()) { //if the user has reached their max bookings
            return "ERROR: Limit reached";
        }

        // count event seats
        int eventCount = 0;
        for (int i = 0; i < allBookings.size(); i++) { //loop through all bookings
            if (allBookings.get(i).event.eventId.equals(event.eventId)) {
                if (allBookings.get(i).status == BookingStatus.CONFIRMED) { //if the booking is confirmed
                    eventCount++;
                }
            }
        }

        String newId = "BK" + nextBookingId++; //generate a new booking id

        // check capacity and book
        if (eventCount < event.capacity) { //if the event has capacity
            allBookings.add(new Booking(newId, user, event, BookingStatus.CONFIRMED));
            return "SUCCESS: Confirmed " + newId;
        } else {
            allBookings.add(new Booking(newId, user, event, BookingStatus.WAITLISTED)); //add the booking to the waitlist
            return "SUCCESS: Waitlisted " + newId;
        }
    }

    public String cancelBooking(String targetId) { //cancel booking method  
        Booking target = null; //find the booking

        // find it
        for (int i = 0; i < allBookings.size(); i++) { //loop through all bookings
            if (allBookings.get(i).bookingId.equals(targetId)) { //if the booking id matches the target id
                target = allBookings.get(i);
                break;
            }
        }

        if (target == null) { //if the booking is not found
            return "ERROR: Not found";
        } else if (target.status == BookingStatus.CANCELLED) { //if the booking is already cancelled
            return "ERROR: Already cancelled";
        }

        // remember if they had a real seat
        boolean wasConfirmed = false; //remember if they had a real seat
        if (target.status == BookingStatus.CONFIRMED) {
            wasConfirmed = true; //if the booking is confirmed
        }

        // cancel it
        target.status = BookingStatus.CANCELLED; //cancel the booking
        String msg = "SUCCESS: Cancelled " + targetId;

        // promote someone if a seat opened up
        if (wasConfirmed == true) { //if the booking was confirmed
            for (int i = 0; i < allBookings.size(); i++) { //loop through all bookings
                Booking b = allBookings.get(i); //get the booking
                
                if (b.event.eventId.equals(target.event.eventId)) { //if the event id matches the target event id
                    if (b.status == BookingStatus.WAITLISTED) { //if the booking is waitlisted
                        b.status = BookingStatus.CONFIRMED; //promote the booking to confirmed
                        msg += " PROMOTED: " + b.bookingId; //add the booking id to the message
                        break; // stop after finding the first one (first come, first served)
                    }
                }
            }
        }

        return msg;
    }
}