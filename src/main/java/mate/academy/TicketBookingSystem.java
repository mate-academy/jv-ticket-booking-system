package mate.academy;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class TicketBookingSystem {
    private final Semaphore semaphore;
    private final AtomicInteger atomicInteger;

    public TicketBookingSystem(int totalSeats) {
        this.atomicInteger = new AtomicInteger(totalSeats);
        this.semaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        BookingResult result;
        try {
            semaphore.acquire();
            if (atomicInteger.get() == 0) {
                result = new BookingResult(user, false, "No seats available.");
                System.out.println(result);
            } else {
                atomicInteger.decrementAndGet();
                result = new BookingResult(user, true, "Booking successful.");
                System.out.println(result);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("Interrupted exception while attempting to book a ticket.");
        } finally {
            semaphore.release();
        }
        return result;
    }
}
