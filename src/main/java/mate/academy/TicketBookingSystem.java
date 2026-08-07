package mate.academy;

import java.util.Objects;
import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private final Semaphore seats;

    public TicketBookingSystem(int totalSeats) {
        if (totalSeats < 0) {
            throw new IllegalArgumentException("totalSeats must be >= 0");
        }
        this.seats = new Semaphore(totalSeats, true);
    }

    public BookingResult attemptBooking(String user) {
        String safeUser = Objects.toString(user, "");
        if (seats.tryAcquire()) {
            return new BookingResult(safeUser, true, "Booking successful.");
        }
        return new BookingResult(safeUser, false, "No seats available.");
    }
}
