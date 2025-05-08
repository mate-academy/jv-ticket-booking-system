package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {

    private final Semaphore seatsAvailable;

    public TicketBookingSystem(int totalSeats) {
        this.seatsAvailable = new Semaphore(totalSeats, true);
    }

    public BookingResult attemptBooking(String user) {
        try {
            boolean acquired = seatsAvailable.tryAcquire();

            if (acquired) {
                return new BookingResult(user, true, "Booking successful.");
            } else {
                return new BookingResult(user, false, "No seats available.");
            }
        } catch (Exception e) {
            return new BookingResult(user, false, "The execution was interrupted");
        }
    }
}
