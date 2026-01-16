package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private final Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {
        this.semaphore = new Semaphore(totalSeats);
    }

    public synchronized BookingResult attemptBooking(String user) {
        if (semaphore.availablePermits() > 0) {
            try {
                semaphore.acquire();
                return new BookingResult(user, true, "Booking successful.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return new BookingResult(user, false, "Error: " + e.getMessage());
            }
        } else {
            return new BookingResult(user, false, "No seats available.");
        }
    }
}
