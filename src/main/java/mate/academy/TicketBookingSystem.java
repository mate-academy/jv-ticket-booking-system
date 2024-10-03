package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private static final String SUCCESS_OPERATION = "Booking successful.";
    private static final String FAILED_OPERATION = "No seats available.";
    private final Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {
        semaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        try {
            if (semaphore.tryAcquire()) {
                return new BookingResult(user, true, SUCCESS_OPERATION);
            } else {
                return new BookingResult(user, false, FAILED_OPERATION);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
