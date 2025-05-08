package mate.academy;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class TicketBookingSystem {
    private static final String FAIL = "No seats available.";
    private static final String SUCCESS = "Booking successful.";
    private static final int TIMEOUT = 300;
    private final Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {
        this.semaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        try {
            return semaphore.tryAcquire(TIMEOUT, TimeUnit.MILLISECONDS)
                    ? getSuccessfulBookingResult(user)
                    : getUnsuccessfulBookingResult(user);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private BookingResult getSuccessfulBookingResult(String user) {
        return new BookingResult(user, true, SUCCESS);
    }

    private BookingResult getUnsuccessfulBookingResult(String user) {
        return new BookingResult(user, false, FAIL);
    }
}
