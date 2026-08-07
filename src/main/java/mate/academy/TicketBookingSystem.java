package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private static final String SUCCESS = "Booking successful.";
    private static final String FAIL = "No seats available.";
    private final Semaphore semaphore;
    private int totalSeats;

    public TicketBookingSystem(int totalSeats) {
        this.totalSeats = totalSeats;
        semaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        if (semaphore.tryAcquire()) {
            if (totalSeats > 0) {
                totalSeats--;
                return new BookingResult(user, true, SUCCESS);
            }
        }
        return new BookingResult(user, false, FAIL);
    }
}
