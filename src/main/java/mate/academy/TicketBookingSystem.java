package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private final Semaphore seats;

    public TicketBookingSystem(int totalSeats) {
        this.seats = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        if (seats.availablePermits() == 0) {
            return new BookingResult(user, false, "No seats available.");
        }
        if (seats.tryAcquire()) {
            return new BookingResult(user, true, "Booking successful.");
        } else {
            return new BookingResult(user, false, "Booking failed for " + user);
        }
    }
}
