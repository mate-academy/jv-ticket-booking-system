package mate.academy;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class TicketBookingSystem {
    private final Semaphore availableSeats;
    private final AtomicInteger remainedSeats;

    public TicketBookingSystem(int totalSeats) {
        this.remainedSeats = new AtomicInteger(totalSeats);
        availableSeats = new Semaphore(totalSeats, true);
    }

    public BookingResult attemptBooking(String user) {
        /*if (availableSeats.tryAcquire()) {
            return new BookingResult(user, true, "Booking successful.");
        } else {
            return new BookingResult(user, false, "No seats available.");
        }*/
        BookingResult result = new BookingResult(user, false, "No seats available.");
        try {
            availableSeats.acquire();
            if (remainedSeats.get() > 0) {
                result = new BookingResult(user, true, "Booking successful.");
                remainedSeats.decrementAndGet();
            }
            availableSeats.release();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
        return result;
    }
}
