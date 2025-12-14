package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private Semaphore semaphore;
    private int totalSeats;

    public TicketBookingSystem(int totalSeats) {
        this.totalSeats = totalSeats;
        this.semaphore = new Semaphore(totalSeats, true);
    }

    public BookingResult attemptBooking(String user) {
        try {
            if (!semaphore.tryAcquire()) {
                return new BookingResult(user, false, "No seats available.");
            }

            return new BookingResult(user, true, "Booking successful.");
        } catch (Exception e) {
            return new BookingResult(user, false, "Something went wrong for " + user);
        }
    }
}
