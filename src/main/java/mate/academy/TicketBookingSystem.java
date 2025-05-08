package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private final Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {
        semaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {

        if (semaphore.tryAcquire()) {
            boolean success = true;
            return new BookingResult(user, success, "Booking successful.");
        }
        boolean noSeats = false;
        return new BookingResult(user, noSeats, "No seats available.");
    }
}
