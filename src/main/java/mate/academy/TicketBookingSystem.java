package mate.academy;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private Semaphore semaphore;
    private int totalSeats;
    private Set<String> bookedUsers;

    public TicketBookingSystem(int totalSeats) {
        semaphore = new Semaphore(totalSeats);
        this.totalSeats = totalSeats;
        bookedUsers = new HashSet<>();
    }

    public BookingResult attemptBooking(String user) {
        if (semaphore.tryAcquire()) {
            if (bookedUsers.contains(user)) {
                semaphore.release();
                return new BookingResult(user, false, "User already booked");
            }
            bookedUsers.add(user);
            return new BookingResult(user, true, "Booking successful.");
        } else {
            return new BookingResult(user, false, "No seats available.");
        }
    }
}
