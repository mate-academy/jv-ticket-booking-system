package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private volatile int totalSeats;
    private Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {
        this.totalSeats = totalSeats;
        this.semaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        try {
            if (semaphore.tryAcquire()) {
                synchronized (this) {
                    totalSeats--;
                }
                return new BookingResult(user, true, "Booking successful.");
            } else {
                return new BookingResult(user, false, "No seats available.");
            }
        } finally {
            if (semaphore.availablePermits() > totalSeats)
                semaphore.release();
        }
    }
}
