package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {

    private final Semaphore semaphore;

    public TicketBookingSystem(int totalSets) {
        if (totalSets <= 0) {
            throw new IllegalArgumentException("totalSets must be greater than 0");
        }
        this.semaphore = new Semaphore(totalSets);
    }

    public BookingResult attemptBooking(String user) {
        if (user == null || user.isEmpty()) {
            return new BookingResult(user, false, "invalid user");
        }

        if (semaphore.tryAcquire()) {
            try {
                Thread.sleep(100);
                return new BookingResult(user, true, "Booking successful.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return new BookingResult(user, false, "Booking interrupted.");
            }
        } else {
            return new BookingResult(user, false, "No seats available.");
        }

    }
}
