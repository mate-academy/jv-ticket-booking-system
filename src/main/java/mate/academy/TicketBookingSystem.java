package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private static final String SUCCESS_MESSAGE = "Booking successful.";
    private static final String FAIL_MESSAGE = "No seats available.";
    private final Semaphore semaphore;
    private int totalSeats;

    public TicketBookingSystem(int totalSeats) {
        this.semaphore = new Semaphore(totalSeats);
        this.totalSeats = totalSeats;
    }

    public BookingResult attemptBooking(String user) {
        if (semaphore.tryAcquire() && totalSeats > 0) {
            totalSeats--;
            return new BookingResult(user, true, SUCCESS_MESSAGE);
        }
        return new BookingResult(user, false, FAIL_MESSAGE);
    }
}
