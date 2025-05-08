package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private final Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {
        this.semaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        try {
            if (semaphore.tryAcquire()) {
                return new BookingResult(user, true, "Booking successful.");
            } else {
                return new BookingResult(user, false, "No seats available.");
            }
        } catch (RuntimeException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}
