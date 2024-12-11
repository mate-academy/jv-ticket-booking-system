package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private final Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {
        if (totalSeats < 1) {
            throw new IllegalArgumentException("totalSeats must be greater than 0");
        }
        semaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        if (user == null || user.isEmpty()) {
            throw new IllegalArgumentException("invalid user");
        }
        if (semaphore.tryAcquire()) {
            return new BookingResult(user, true, "Booking successful.");
        } else {
            return new BookingResult(user, false, "No seats available.");
        }
    }
}
