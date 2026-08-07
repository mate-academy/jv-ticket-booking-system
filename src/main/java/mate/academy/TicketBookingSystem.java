package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private final Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {
        semaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        boolean success = false;
        String message;
        if (semaphore.tryAcquire()) {
            success = true;
            message = "Booking successful.";
        } else {
            message = "No seats available.";
        }

        return new BookingResult(user, success, message);
    }
}
