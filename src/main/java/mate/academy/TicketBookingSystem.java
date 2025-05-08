package mate.academy;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class TicketBookingSystem {
    private static final String BOOKING_SUCCESSFUL_MESSAGE = "Booking successful.";
    private static final String NO_SEATS_AVAILABLE_MESSAGE = "No seats available.";
    private static final String THE_EXECUTION_WAS_INTERRUPTED_MESSAGE =
            "The execution was interrupted";
    private static final int NO_SEATS_AVAILABLE = 0;

    private final Semaphore semaphore;
    private final AtomicInteger availableSeats;

    public TicketBookingSystem(final int totalSeats) {
        semaphore = new Semaphore(totalSeats);
        availableSeats = new AtomicInteger(totalSeats);
    }

    public BookingResult attemptBooking(final String user) {
        try {
            semaphore.acquire();
            if (availableSeats.get() > NO_SEATS_AVAILABLE) {
                availableSeats.decrementAndGet();
                return new BookingResult(user, true, BOOKING_SUCCESSFUL_MESSAGE);
            }
            return new BookingResult(user, false, NO_SEATS_AVAILABLE_MESSAGE);
        } catch (final InterruptedException exception) {
            Thread.currentThread().interrupt();
            return new BookingResult(user, false, THE_EXECUTION_WAS_INTERRUPTED_MESSAGE);
        } finally {
            semaphore.release();
        }
    }
}
