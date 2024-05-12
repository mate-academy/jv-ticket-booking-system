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
            if (totalSeats.get() <= 0) {
                return new BookingResult(user, false, "No seats available.");
            }

            if (!semaphore.tryAcquire()) {
                return new BookingResult(user, false, "No available permits for booking.");
            }

            totalSeats.decrementAndGet();
            return new BookingResult(user, true, "Booking successful.");
        } finally {
            semaphore.release();
        }
    }
}
