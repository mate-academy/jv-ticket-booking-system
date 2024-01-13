package mate.academy;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class TicketBookingSystem {
    private final Semaphore semaphore;
    private AtomicInteger counter;

    public TicketBookingSystem(int totalSeats) {
        this.semaphore = new Semaphore(totalSeats);
        this.counter = new AtomicInteger(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        try {
            semaphore.acquire();
            if (counter.get() > 0) {
                counter.decrementAndGet();
                return new BookingResult(user, true, "Booking successful.");
            }
            return new BookingResult(user, false, "No seats available.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread was interrupted!", e);
        } finally {
            semaphore.release();
        }
    }
}
