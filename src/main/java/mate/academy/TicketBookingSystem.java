package mate.academy;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class TicketBookingSystem {
    private final AtomicInteger totalSeats;
    private final Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {
        this.totalSeats = new AtomicInteger(totalSeats);
        semaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        BookingResult result;
        try {
            semaphore.acquire();
            if (totalSeats.get() > 0) {
                totalSeats.decrementAndGet();
                result = new BookingResult(user, true, "Booking successful.");
            } else {
                result = new BookingResult(user, false, "No seats available.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Error occurred when booking a ticket", e);
        } finally {
            semaphore.release();
        }
        return result;
    }
}
