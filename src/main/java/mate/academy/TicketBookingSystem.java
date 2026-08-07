package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private final Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {
        this.semaphore = new Semaphore(totalSeats, true);
    }

    public BookingResult attemptBooking(String user) {
        // Try to acquire a permit, representing a seat
        if (semaphore.tryAcquire()) {
            return new BookingResult(user, true, "Booking successful.");
        } else {
            return new BookingResult(user, false, "No seats available.");
        }
    }
}
