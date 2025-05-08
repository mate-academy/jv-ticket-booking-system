package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private final Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {
        this.semaphore = new Semaphore(totalSeats, true);
    }

    public BookingResult attemptBooking(String user) {
        if (semaphore.tryAcquire()) {
            try {
                Thread.sleep(10);
                return new BookingResult(user, true, "Booking successful.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        } else {
            return new BookingResult(user, false, "No seats available.");
        }
    }
}
