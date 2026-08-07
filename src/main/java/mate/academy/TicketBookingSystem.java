package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private static final String SUCCESS_MESSAGE = "Booking successful.";
    private static final String NO_SEATS_AVAILABLE_MESSAGE = "No seats available.";
    private static final String EXECUTION_FAILURE_MESSAGE = "The execution was interrupted";

    private final Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {
        semaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        try {
            if (semaphore.tryAcquire()) {
                return new BookingResult(user, true, SUCCESS_MESSAGE);
            } else {
                return new BookingResult(user, false, NO_SEATS_AVAILABLE_MESSAGE);
            }
        } catch (Exception e) {
            return new BookingResult(user, false, EXECUTION_FAILURE_MESSAGE);
        }
    }
}
