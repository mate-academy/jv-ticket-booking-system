package mate.academy;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class TicketBookingSystem {
    private final Semaphore semaphore;
    private final AtomicInteger counter;

    public TicketBookingSystem(int totalSeats) {
        this.semaphore = new Semaphore(totalSeats);
        this.counter = new AtomicInteger(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        if (semaphore.tryAcquire()) {
            try {
                if (counter.get() > 0) {
                    counter.decrementAndGet();
                    return new BookingResult(user, true, "Booking successful.");
                }
            } finally {
                semaphore.release();
            }
        }
        return new BookingResult(user, false, "No seats available.");
    }
}
