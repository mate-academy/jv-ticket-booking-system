package mate.academy;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class TicketBookingSystem {
    private final Semaphore semaphore;

    private final AtomicInteger seats;

    public TicketBookingSystem(int totalSeats) {
        seats = new AtomicInteger(totalSeats);
        semaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        if (seats.get() <= 0) {
            return new BookingResult(user, false, "No seats available.");
        }
        try {
            semaphore.acquire();
            return seats.decrementAndGet() < 0
                    ? new BookingResult(user, false, "No seats available.")
                    : new BookingResult(user, true, "Booking successful.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
        }
    }
}
