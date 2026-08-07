package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private final Semaphore seats;

    public TicketBookingSystem(int totalSeats) {
        this.seats = new Semaphore(totalSeats, true);
    }

    public BookingResult attemptBooking(String user) {
        boolean success = seats.tryAcquire();
        if (success) {
            return new BookingResult(user, true, "Booking successful.");
        } else {
            return new BookingResult(user, false, "No seats available.");
        }
    }

    public int getAvailableSeats() {
        return seats.availablePermits();
    }
}
