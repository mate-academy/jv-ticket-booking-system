package mate.academy;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class TicketBookingSystem {
    private final Semaphore semaphore;
    private final AtomicInteger atomicInteger;

    public TicketBookingSystem(int totalSeats) {
        this.semaphore = new Semaphore(totalSeats);
        this.atomicInteger = new AtomicInteger(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        if (semaphore.tryAcquire()) {
            try {
                int remainingSeats = atomicInteger.decrementAndGet();
                if (remainingSeats >= 0) {
                    return new BookingResult(null, true, "Booking successful.");
                } else {
                    atomicInteger.incrementAndGet();
                    return new BookingResult(null, false, "No seats available.");
                }
            } finally {
                semaphore.release();
            }
        }
        return new BookingResult(null, false, "No seats available.");
    }
}
