package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private Semaphore semaphore;
    private int totalSeats;

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
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error during booking", e);
        }
    }
}
