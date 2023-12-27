package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private int totalSeats;
    private Semaphore semaphore = new Semaphore(totalSeats);

    public TicketBookingSystem(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public BookingResult attemptBooking(String user) {
        BookingResult result = null;
        try {
            semaphore.acquire();
            result = new BookingResult(user, true, "You booked a seat!");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            result = new BookingResult(user, false, "There isn't an available seat! Try later.");
        } finally {
            semaphore.release();
        }
        return result;
    }
}
