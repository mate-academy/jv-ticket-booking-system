package mate.academy;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class TicketBookingSystem {
    private AtomicInteger totalSeats;
    private final Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {
        this.totalSeats = new AtomicInteger(totalSeats);
        semaphore = new Semaphore(totalSeats, true);
    }

    public BookingResult attemptBooking(String user) {
        if (totalSeats.get() == 0) {
            return new BookingResult(user, false, "No seats available.");
        }
        try {
            semaphore.acquire();
            totalSeats.decrementAndGet();
            return new BookingResult(user, true, "Booking successful.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
        }
    }
}
