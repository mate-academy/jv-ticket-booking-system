package mate.academy;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class TicketBookingSystem {
    private static final String NO_SEATS_MESSAGE = "No seats available.";
    private static final String SUCCESS_BOOKING_MESSAGE = "Booking successful.";

    private final Semaphore semaphore;
    private final AtomicInteger availableSeats;

    public TicketBookingSystem(int totalSeats) {
        semaphore = new Semaphore(totalSeats, true);
        this.availableSeats = new AtomicInteger(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        BookingResult bookingResult = new BookingResult(user, false, NO_SEATS_MESSAGE);
        try {
            semaphore.acquire();
            if (availableSeats.get() > 0) {
                bookingResult = new BookingResult(user, true, SUCCESS_BOOKING_MESSAGE);
                availableSeats.decrementAndGet();
            }
            semaphore.release();
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(ex);
        }
        return bookingResult;
    }
}
