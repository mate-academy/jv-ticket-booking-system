package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private final int totalSeats;
    private final Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {
        this.totalSeats = totalSeats;
        this.semaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
         if (semaphore.tryAcquire()) {
           return new BookingResult(user, true, "Booking successful.");
        }
        return new BookingResult(user, false, "No seats available.");
    }
}
