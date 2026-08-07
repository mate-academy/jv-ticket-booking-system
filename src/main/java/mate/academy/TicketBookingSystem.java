package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private static final String BOOKING_SUCCESSFUL = "Booking successful.";
    private static final String BOOKING_CANCELED = "No seats available.";

    private final Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {
        semaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        boolean isAcquire = semaphore.tryAcquire();
        String message = isAcquire ? BOOKING_SUCCESSFUL : BOOKING_CANCELED;
        return new BookingResult(user, isAcquire, message);
    }
}
