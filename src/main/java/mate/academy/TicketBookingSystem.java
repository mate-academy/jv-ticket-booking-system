package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private final Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {
        this.semaphore = new Semaphore(totalSeats, true);
    }

    public BookingResult attemptBooking(String user) {
        try {
            if (!semaphore.tryAcquire()) {
                return new BookingResult(user, false, "No seats available.");
            }

            return new BookingResult(user, true, "Booking successful.");
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            return new BookingResult(user, false, "Booking was interrupted.");
        }
    }
}
