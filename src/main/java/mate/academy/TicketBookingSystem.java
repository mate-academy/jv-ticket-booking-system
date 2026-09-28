package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private final Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {
        semaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        boolean success = semaphore.tryAcquire();
        String message = (success) ? "Booking successful." : "No seats available.";
        return new BookingResult(user, success, message);
    }
}
