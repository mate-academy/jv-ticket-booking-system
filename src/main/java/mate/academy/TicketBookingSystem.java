package mate.academy;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class TicketBookingSystem {
    private Semaphore semaphore;
    private AtomicInteger availableSeats;

    public TicketBookingSystem(int totalSeats) {
        semaphore = new Semaphore(totalSeats);
        availableSeats = new AtomicInteger(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        try {
            semaphore.acquire();
            if (availableSeats.get() > 0) {
                availableSeats.decrementAndGet();
                return new BookingResult(user, true,
                        "Booking successful.");
            }
            return new BookingResult(user, false,
                    "No seats available.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
        }
    }
}
