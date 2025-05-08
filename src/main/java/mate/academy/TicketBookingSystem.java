package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private final Semaphore seats;

    public TicketBookingSystem(int totalSeats) {
        this.seats = new Semaphore(totalSeats, true);
    }

    public BookingResult attemptBooking(String user) {
        try {
            if (seats.tryAcquire()) {
                Thread.sleep(100);
                return new BookingResult(user, true, "Booking successful.");
            } else {
                return new BookingResult(user, false, "No seats available.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}
