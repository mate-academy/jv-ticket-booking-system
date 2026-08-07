package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private final Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {
        semaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        if (semaphore.tryAcquire()) {
            boolean canOrder = true;
            return new BookingResult(user, canOrder, "Booking successful.");
        }
        boolean canOrder = false;
        return new BookingResult(user, canOrder, "No seats available.");
    }
}
