package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private final Semaphore seatsTotal;

    public TicketBookingSystem(int totalSeats) {
        this.seatsTotal = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        if (seatsTotal.tryAcquire()) {
                return new BookingResult(user, true, "Booking successful.");
        }
            return new BookingResult(user, false, "Cannot booking");
    }
}
