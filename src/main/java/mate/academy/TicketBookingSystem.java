package mate.academy;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class TicketBookingSystem {
    private Semaphore semaphore;
    private AtomicInteger availableSeats;

    public TicketBookingSystem(int totalSeats) {
        this.semaphore = new Semaphore(totalSeats);
        this.availableSeats = new AtomicInteger(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        boolean isAvailable = false;
        String message;
        try {
            semaphore.acquire();
            if (availableSeats.get() != 0) {
                availableSeats.decrementAndGet();
                isAvailable = true;
                message = "Booking successful.";
            } else {
                message = "No seats available.";
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
        }
        return new BookingResult(user, isAvailable, message);
    }
}
