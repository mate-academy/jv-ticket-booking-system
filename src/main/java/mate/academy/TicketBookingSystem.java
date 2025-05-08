package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private final Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {
        semaphore = new Semaphore(totalSeats, true);
    }

    public BookingResult attemptBooking(String user) {
        boolean isAcquire = semaphore.tryAcquire();
        return new BookingResult(
                user,
                isAcquire,
                isAcquire ? "Booking successful." : "No seats available."
        );
    }
}
