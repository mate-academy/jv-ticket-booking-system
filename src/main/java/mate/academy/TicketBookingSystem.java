package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private final Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {
        semaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        boolean isSuccess = semaphore.tryAcquire();
        String message = isSuccess ? "Booking successful." : "No seats available.";
        return new BookingResult(user, isSuccess, message);
    }
}
