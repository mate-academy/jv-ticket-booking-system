package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private final Semaphore semaphore;
    private int availableSeats;

    public TicketBookingSystem(int totalSeats) {
        this.availableSeats = totalSeats;
        this.semaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        if (semaphore.tryAcquire()) {
            synchronized (this) {
                if (availableSeats > 0) {
                    availableSeats--;
                    semaphore.release();
                    return new BookingResult(user, true, "Available Seats: " + availableSeats);
                }
            }
        }
        return new BookingResult(user, false, "Not enough seats");
    }
}
