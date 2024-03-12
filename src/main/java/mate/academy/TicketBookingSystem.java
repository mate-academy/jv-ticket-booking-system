package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private final Semaphore semaphore;
    private int totalSeats;

    public TicketBookingSystem(int totalSeats) {
        semaphore = new Semaphore(1);
        this.totalSeats = totalSeats;
    }

    public BookingResult attemptBooking(String user) {
        try {
            semaphore.acquire();
            if (totalSeats > 0) {
                totalSeats--;
                return new BookingResult(
                        user,
                        true,
                        "Booking successful.");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
        }
        return new BookingResult(user, false, "No seats available.");
    }
}
