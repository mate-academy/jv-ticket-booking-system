package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {

    private static final String SUCCESSFUL = "Booking successful.";

    private static final String UNSUCCESSFUL = "No seats available.";

    private Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {
        semaphore = new Semaphore(totalSeats, true);
    }

    public BookingResult attemptBooking(String user) {
        boolean success = semaphore.tryAcquire();
        String message = success ? SUCCESSFUL : UNSUCCESSFUL;
        return new BookingResult(user, success, message);
    }
}
