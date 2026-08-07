package mate.academy;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TicketBookingSystem {
    private final Semaphore semaphore;
    private int seatsNumber;
    private final Lock lock = new ReentrantLock(true);

    public TicketBookingSystem(int totalSeats) {
        this.seatsNumber = totalSeats;
        this.semaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        try {
            semaphore.acquire();
            lock.lock();
            try {
                if (seatsNumber > 0) {
                    seatsNumber--;
                    return new BookingResult(user, true, "Booking successful.");
                } else {
                    return new BookingResult(user, false, "No seats available.");
                }
            } finally {
                lock.unlock();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
        }
    }
}
