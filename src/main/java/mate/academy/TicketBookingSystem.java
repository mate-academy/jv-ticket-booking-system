package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private final int totalSeats;

    private int bookedSeats;

    private final Semaphore semaphore = new Semaphore(1);

    public TicketBookingSystem(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public BookingResult attemptBooking(String user) {
        try {
            semaphore.acquire();
            if (bookedSeats == totalSeats) {
                Thread.currentThread().interrupt();
                return new BookingResult(user, false, "No seats available.");
            }
            bookedSeats += 1;
            return new BookingResult(user, true, "Booking successful.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
        }
    }
}
