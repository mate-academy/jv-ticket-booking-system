package mate.academy;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class TicketBookingSystem {
    private final Semaphore semaphore;
    private AtomicInteger totalSeats;

    public TicketBookingSystem(int totalSeats) {
        this.totalSeats = new AtomicInteger(totalSeats);
        semaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        try {
            if (totalSeats.get() > 0) {
                semaphore.acquire();
                BookingResult bookingResult = new BookingResult(user, true, "Booking successful.");
                totalSeats.decrementAndGet();
                semaphore.release();
                return bookingResult;
            } else {
                return new BookingResult(user, false, "No seats available.");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
