package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private final Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {
        this.semaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        return semaphore.tryAcquire() ?
                new BookingResult(user, true, "Booking successful.")
                : new BookingResult(user, false, "No seats available.");
    }
}
