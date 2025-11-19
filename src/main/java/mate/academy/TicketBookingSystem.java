package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {

    private final Semaphore totalSeats;

    public TicketBookingSystem(int totalSeats) {
        this.totalSeats = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        return totalSeats.tryAcquire()
                ? new BookingResult(user, true, "Booking successful.")
                : new BookingResult(user, false, "No seats available.");
    }
}
