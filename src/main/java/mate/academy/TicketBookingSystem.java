package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {
        this.semaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        BookingResult result = null;
        if (semaphore.tryAcquire()) {
            result = new BookingResult(user, true, "Booking successful.");
        } else {
            result = new BookingResult(user, false, "No seats available.");
        }
        return result;
    }
}
