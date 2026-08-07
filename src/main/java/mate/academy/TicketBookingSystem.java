package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private final Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {
        semaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        boolean isAvailable = semaphore.tryAcquire();
        String message = isAvailable
                ? "Booking successful."
                : "No seats available.";
        return new BookingResult(user, isAvailable, message);
    }
}
