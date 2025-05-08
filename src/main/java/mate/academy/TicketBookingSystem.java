package mate.academy;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class TicketBookingSystem {
    private final Semaphore semaphore;
    private final AtomicInteger seatsCounter;

    public TicketBookingSystem(int totalSeats) {
        semaphore = new Semaphore(totalSeats);
        this.seatsCounter = new AtomicInteger(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        try {
            semaphore.acquire();
            if (seatsCounter.get() > 0) {
                seatsCounter.decrementAndGet();
                return new BookingResult(user, true, "Booking successful.");
            } else {
                throw new InterruptedException();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return new BookingResult(user, false, "No seats available.");
        } finally {
            semaphore.release();
        }
    }
}
