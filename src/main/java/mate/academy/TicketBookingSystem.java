package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private int totalSeats;

    public TicketBookingSystem(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public BookingResult attemptBooking(String user) {
        Semaphore semaphore = new Semaphore(totalSeats + 1);
        try {
            semaphore.acquire();
            if (totalSeats > 0) {
                totalSeats--;
                return new BookingResult(user, true, "Booking successful.");
            }
            return new BookingResult(user, false, "No seats available.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
        }
    }
}
