package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private Semaphore semaphore = new Semaphore(1);

    private int totalSeats;

    public TicketBookingSystem(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public BookingResult attemptBooking(String user) {
        while (totalSeats > 0) {
            try {
                semaphore.acquire();
                totalSeats = totalSeats - 1;
                semaphore.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return new BookingResult(user, true, "Booking successful.");
        }
        return new BookingResult(user, false, "No seats available.");
    }
}
