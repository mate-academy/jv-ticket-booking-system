package mate.academy;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class TicketBookingSystem {
    private AtomicInteger totalSeats;
    private final Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {
        this.totalSeats = new AtomicInteger(totalSeats);
        this.semaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        try {
            semaphore.acquire();

            if (totalSeats.get() > 0) {
                totalSeats.decrementAndGet();
                return new BookingResult(user, true, "Booking successful.");
            }

            return new BookingResult(user, false, "No seats available.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
        }
    }
}
