package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private final Semaphore countSeats;

    public TicketBookingSystem(int totalSeats) {
        this.countSeats = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        if (countSeats.tryAcquire()) {
            return new BookingResult(user, true, "Booking successful.");
        } else {
            return new BookingResult(user, false, "No seats available.");
        }
    }
}
