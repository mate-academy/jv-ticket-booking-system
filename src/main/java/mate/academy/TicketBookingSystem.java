package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {
        semaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        boolean success = semaphore.tryAcquire();
        return new BookingResult(user, success, success ? "Booking successful."
                : "No seats available.");
    }
}
