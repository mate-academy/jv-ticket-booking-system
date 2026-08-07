package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {

    public static final String BOOKING_SUCCESSFUL = "Booking successful.";
    public static final String BOOKING_UNAVAILABLE = "No seats available.";
    private final Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {
        semaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        if (semaphore.tryAcquire()) {
            return new BookingResult(user, true, BOOKING_SUCCESSFUL);
        }
        return new BookingResult(user, false, BOOKING_UNAVAILABLE);
    }
}
