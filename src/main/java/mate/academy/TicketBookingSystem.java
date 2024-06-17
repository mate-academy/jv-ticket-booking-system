package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private final Semaphore seatsSemaphore;

    public TicketBookingSystem(int totalSeats) {
        seatsSemaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        boolean acquired;
        try {
            acquired = seatsSemaphore.tryAcquire();
            if (acquired) {
                return new BookingResult(user, true, "Booking successful.");
            }
            return new BookingResult(user, false, "No seats available.");
        } catch (Exception e) {
            throw new RuntimeException("Error while attempting to book seats.", e);
        }
    }
}
