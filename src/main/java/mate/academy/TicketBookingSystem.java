package mate.academy;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TicketBookingSystem {
    private final Semaphore semaphore;
    private int totalSeats;
    private final Lock lock;

    public TicketBookingSystem(int totalSeats) {
        this.totalSeats = totalSeats;
        this.semaphore = new Semaphore(totalSeats);
        this.lock = new ReentrantLock();
    }

    public BookingResult attemptBooking(String user) {
        BookingResult bookingResult;
        lock.lock();
        try {
            semaphore.acquire();
            if (totalSeats > 0) {
                bookingResult = new BookingResult(user, true, "Booking successful.");
                totalSeats--;
            } else {
                bookingResult = new BookingResult(user, false, "No seats available.");
            }
            semaphore.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
        return bookingResult;
    }
}
