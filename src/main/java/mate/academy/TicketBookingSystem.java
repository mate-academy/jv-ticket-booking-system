package mate.academy;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TicketBookingSystem {
    public static final String MESSAGE_SUCCESS = "Booking successful.";
    public static final String MESSAGE_FAIL = "No seats available.";
    private int totalSeats;
    private final Semaphore semaphore;
    private final Lock lock;

    public TicketBookingSystem(int totalSeats) {
        this.totalSeats = totalSeats;
        this.semaphore = new Semaphore(totalSeats);
        this.lock = new ReentrantLock();
    }

    public BookingResult attemptBooking(String user) {
        BookingResult result;
        lock.lock();
        try {
            semaphore.acquire();
            if (totalSeats != 0) {
                result = new BookingResult(user, true, MESSAGE_SUCCESS);
                totalSeats--;
            } else {
                result = new BookingResult(user, false, MESSAGE_FAIL);
            }
            semaphore.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
        return result;
    }
}
