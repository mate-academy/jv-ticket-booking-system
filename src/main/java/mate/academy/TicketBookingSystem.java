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
        BookingResult bookingResult = null;
        try {
            semaphore.acquire();
            if (totalSeats.get() != 0) {
                bookingResult = new BookingResult(user, true, "Booking successful.");
                totalSeats.decrementAndGet();
            } else {
                bookingResult = new BookingResult(user, false, "No seats available.");
            }
            semaphore.release();
        } catch (InterruptedException e) {
            bookingResult = new BookingResult(user, false, "No seats available.");
        }
        return bookingResult;
    }
}
