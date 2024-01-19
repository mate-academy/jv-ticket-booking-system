package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private final Semaphore availableSeats;

    public TicketBookingSystem(int totalSeats) {
        availableSeats = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        if (availableSeats.tryAcquire()) {
            return new BookingResult(user, true, "Booking successful.");
        }
        return new BookingResult(user, false, "No seats available.");
    }
}
