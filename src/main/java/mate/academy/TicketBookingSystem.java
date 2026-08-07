package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private static final String BOOKING_SUCCESSFUL = "Booking successful.";
    private static final String NO_SEATS_AVAILABLE = "No seats available.";
    private final Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {
        semaphore = new Semaphore(totalSeats, true);
    }

    public BookingResult attemptBooking(String user) {
        boolean isAcquire = semaphore.tryAcquire();
        return new BookingResult(user, isAcquire,
                isAcquire ? BOOKING_SUCCESSFUL : NO_SEATS_AVAILABLE);
    }
}
