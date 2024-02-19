package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private final Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {
        this.semaphore = new Semaphore(totalSeats, true);
    }

    public BookingResult attemptBooking(String user) {
        boolean success = false;
        String message = "";

        try {
            if (semaphore.tryAcquire()) {
                success = true;
                message = "Booking successful.";
            } else {
                message = "No seats available.";
            }
        } catch (Exception e) {
            message = "Booking attempt failed due to an error.";
        }

        return new BookingResult(user, success, message);
    }
}
