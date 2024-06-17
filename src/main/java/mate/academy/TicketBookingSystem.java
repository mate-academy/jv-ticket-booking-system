package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private static final String SUCCESS_MSG = "Booking successfull";
    private static final String NO_TICKETS_AVAILABLE_MSG = "No tickets available";
    private final Semaphore semaphore;
    
    public TicketBookingSystem(int totalSeats) {
        semaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        try {
            if (semaphore.tryAcquire()) {
                return new BookingResult(user, true, SUCCESS_MSG);
            } else {
                return new BookingResult(user, false, NO_TICKETS_AVAILABLE_MSG);
            }
        } catch (Throwable t) {
            throw new RuntimeException("Error while attempting to book tickets", t);
        }
    }
}
