package mate.academy;

import java.util.concurrent.ForkJoinWorkerThread;
import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private final Semaphore semaphore;
    private Integer totalSeats;

    public TicketBookingSystem(int totalSeats) {
        this.semaphore = new Semaphore(totalSeats);
        this.totalSeats = totalSeats;
    }

    public BookingResult attemptBooking(String user) {
        try {
            semaphore.acquire();
            if (totalSeats > 0) {
                totalSeats--;
                return new BookingResult(user, true, "Booking successful.");
            } else {
                return new BookingResult(user, false, "No seats available.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread was interrupted!", e);
        } finally {
            semaphore.release();
        }
    }
}
