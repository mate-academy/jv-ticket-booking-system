package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private static final boolean SUCCESS = true;
    private static final boolean FAIL = false;
    private static final String SUCCESS_MESSAGE = "Booking successful.";
    private static final String FAIL_MESSAGE = "No seats available.";
    private Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {
        this.semaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        if (semaphore.tryAcquire()) {
            return new BookingResult(user, SUCCESS, SUCCESS_MESSAGE);
        }
        return new BookingResult(user, FAIL, FAIL_MESSAGE);
    }
}
