package mate.academy;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class TicketBookingSystem {
    private final Semaphore semaphore;
    private final AtomicInteger totalSeats;

    public TicketBookingSystem(int totalSeats) {
        this.totalSeats = new AtomicInteger(totalSeats);
        semaphore = new Semaphore(totalSeats, true);
    }

    public BookingResult attemptBooking(String user) {
        if (totalSeats.get() > 0) {
            BookingResult bookingResult;
            try {
                semaphore.acquire();
                totalSeats.decrementAndGet();
                Thread.sleep(100);
                bookingResult = new BookingResult(user, true, "Booking successful.");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            semaphore.release();
            return bookingResult;
        } else {
            return new BookingResult(user, false, "No seats available.");
        }
    }
}
