package mate.academy;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class TicketBookingSystem {
    private final Semaphore semaphore;
    private final AtomicInteger availableSeats;

    public TicketBookingSystem(int totalSeats) {
        this.semaphore = new Semaphore(totalSeats);
        this.availableSeats = new AtomicInteger(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        synchronized (this) {
            if (availableSeats.get() == 0) {
                return new BookingResult(user, false, "No seats available.");
            }

            try {
                semaphore.acquire();
                BookingResult bookingResult = new BookingResult(user, true, "Booking successful.");
                availableSeats.decrementAndGet();
                return bookingResult;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return new BookingResult(user, false, "Booking failed.");
            }
        }
    }
}
