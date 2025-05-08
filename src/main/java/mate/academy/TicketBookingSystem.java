package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    public static final String FAIL_MESSAGE = "No seats available.";
    private static final String SUCCESS_MESSAGE = "Booking successful.";

    private final Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {
        semaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        return semaphore.tryAcquire()
                ? new BookingResult(user, true, SUCCESS_MESSAGE)
                : new BookingResult(user, false, FAIL_MESSAGE);
    }
}
