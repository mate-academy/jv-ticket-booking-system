package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private final Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {
        semaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        BookingResult bookingResult;
        if (semaphore.tryAcquire()) {
            bookingResult = new BookingResult(user, true, "Booking successful.");
        } else {
            bookingResult = new BookingResult(user, false, "No seats available.");
        }
        return bookingResult;
    }
}
