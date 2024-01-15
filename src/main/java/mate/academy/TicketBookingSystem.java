package mate.academy;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TicketBookingSystem {
    private final Lock lock = new ReentrantLock();
    private final Semaphore semaphore;
    private int totalSeats;

    public TicketBookingSystem(int totalSeats) {
        this.totalSeats = totalSeats;
        semaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        try {
            semaphore.acquire();
            if (totalSeats > 0) {
                synchronized (lock) {
                    totalSeats--;
                }
                return new BookingResult(user, true, "Booking successful.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("The execution was interrupted", e);
        } finally {
            semaphore.release();
        }
        return new BookingResult(user, false, "No seats available.");
    }
}
