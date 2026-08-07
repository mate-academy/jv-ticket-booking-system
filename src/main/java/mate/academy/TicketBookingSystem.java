package mate.academy;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class TicketBookingSystem {

    private final Semaphore semaphore;
    private final AtomicInteger bookedCount = new AtomicInteger(0);

    public TicketBookingSystem(int totalSeats) {
        this.semaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        if (semaphore.tryAcquire()) {
            bookedCount.incrementAndGet();
            return new BookingResult(user,true, "Booking successful.");
        }
        return new BookingResult(user, false, "No seats available.");
    }

}
