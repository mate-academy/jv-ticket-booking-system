package mate.academy;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class TicketBookingSystem {
    private final int totalSeats;
    private final Semaphore semaphore;
    private final AtomicInteger seatsBooked = new AtomicInteger(0);

    public TicketBookingSystem(int totalSeats) {
        this.totalSeats = totalSeats;
        this.semaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        try {
            semaphore.acquire();
            BookingResult bookingResult;
            if (seatsBooked.get() < totalSeats) {
                seatsBooked.incrementAndGet();
                bookingResult = new BookingResult(user, true, "Booking successful.");
            } else {
                bookingResult = new BookingResult(user, false, "No seats available.");
            }
            semaphore.release();
            return bookingResult;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}
