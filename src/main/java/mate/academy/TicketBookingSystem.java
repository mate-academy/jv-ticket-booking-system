package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private int totalSeats;
    private final Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {
        this.totalSeats = totalSeats;
        this.semaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        try {
            if (totalSeats <= 0) {
                return new BookingResult(user, false, "No seats available.");
            }

            semaphore.acquire(totalSeats);
            totalSeats--;
            return new BookingResult(user, true, "Booking successful.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
        }
    }
}
