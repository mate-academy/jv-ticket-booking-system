package mate.academy;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class TicketBookingSystem {
    private final Semaphore semaphore;
    private final AtomicInteger totalSeats;

    public TicketBookingSystem(int totalSeats) {
        this.semaphore = new Semaphore(totalSeats);
        this.totalSeats = new AtomicInteger(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        try {
            semaphore.acquire();
            int seats = totalSeats.get();
            if (seats > 0) {
                totalSeats.decrementAndGet();
                return new BookingResult(user, true, "Booking successful.");
            } else {
                return new BookingResult(user, false, "No seats available.");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
        }
    }
}
