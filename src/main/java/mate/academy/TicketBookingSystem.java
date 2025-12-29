package mate.academy;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class TicketBookingSystem {
    private final Semaphore semaphore;
    private final AtomicInteger ticketNumber;

    public TicketBookingSystem(int totalSeats) {
        semaphore = new Semaphore(totalSeats);
        ticketNumber = new AtomicInteger(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        boolean acquired = false;
        try {
            semaphore.acquire();
            acquired = true;
            if (ticketNumber.get() <= 0) {
                return new BookingResult(user, false, "No seats available.");
            } else {
                ticketNumber.decrementAndGet();
                return new BookingResult(user, true, "Booking successful.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return new BookingResult(user, false, "Booking failed.");
        } finally {
            if (acquired) {
                semaphore.release();
            }
        }
    }
}
