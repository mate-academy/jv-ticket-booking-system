package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {
        this.semaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        try {
            semaphore.acquire();
            return new BookingResult(user, true, "Booking successful.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
