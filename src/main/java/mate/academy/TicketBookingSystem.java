package mate.academy;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class TicketBookingSystem {
    private Semaphore semaphore;
    private AtomicInteger numberOfSeats;

    public TicketBookingSystem(int totalSeats) {
        this.semaphore = new Semaphore(totalSeats);
        numberOfSeats = new AtomicInteger(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        try {
            semaphore.acquire();
            if (numberOfSeats.get() > 0) {
                numberOfSeats.decrementAndGet();
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
