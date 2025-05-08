package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {

    private final Semaphore availableSeats;

    public TicketBookingSystem(int totalSeats) {
        this.availableSeats = new Semaphore(totalSeats, true);
    }

    public BookingResult attemptBooking(String user) {
        if (availableSeats.tryAcquire()) {
            return new BookingResult(user, true, "Booking successful.");
        } else {
            return new BookingResult(user, false, "No seats available.");
        }
    }
}
