package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {

    private final Semaphore countSemaphore;

    public TicketBookingSystem(int totalSeats) {
        countSemaphore = new Semaphore(totalSeats, true);
    }

    public BookingResult attemptBooking(String user) {
        return countSemaphore.tryAcquire()
                ? new BookingResult(user, true, "Booking successful.")
                : new BookingResult(user, false, "No seats available.");
    }
}
