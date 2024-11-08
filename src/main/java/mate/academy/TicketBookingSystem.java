package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private final Semaphore seatsTotal;

    public TicketBookingSystem(int totalSeats) {
        seatsTotal = new Semaphore(totalSeats, true);
    }

    public BookingResult attemptBooking(String user) {
        if (seatsTotal.tryAcquire()) {
            try {
                return new BookingResult(user, true, "Booking successful.");
            } finally {
                seatsTotal.release();
            }
        } else {
            return new BookingResult(user, false, "Cannot booking");
        }
    }
}
