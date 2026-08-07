package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private static final String SUCCESS_MESSAGE = "Booking successful.";
    private static final String FAIL_MESSAGE = "No seats available.";
    private final Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {
        this.semaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        if (semaphore.tryAcquire()) {
            return new BookingResult(user, true, SUCCESS_MESSAGE);
        } else {
            return new BookingResult(user, false, FAIL_MESSAGE);
        }
    }
}
