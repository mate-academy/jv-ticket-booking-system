package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private final Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {

        this.semaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        boolean success = false;
        String message = "No seats available.";

        if (semaphore.tryAcquire()) {
            try {
                success = true;
                message = "Booking successful.";
            } finally {
                System.err.println();
            }
        }
        return new BookingResult(user, success, message);
    }
}
