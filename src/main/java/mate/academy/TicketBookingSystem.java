package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private final Semaphore seatsSemaphore;

    public TicketBookingSystem(int totalSeats) {
        this.seatsSemaphore = new Semaphore(totalSeats, true);
    }

    public BookingResult attemptBooking(String user) {
        try {
            if (seatsSemaphore.tryAcquire()) {
                return new BookingResult(user, true, "Booking successful.");
            } else {
                return new BookingResult(user, false, "No seats available.");
            }
        } catch (Exception e) {
            return new BookingResult(user, false, "An error occurred " + e.getMessage());
        }
    }
}
