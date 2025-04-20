package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private final Semaphore semaphore;
    private int availableSeats;

    public TicketBookingSystem(int totalSeats) {
        this.semaphore = new Semaphore(totalSeats, true);
        this.availableSeats = totalSeats;
    }

    public BookingResult attemptBooking(String user) {
        if (availableSeats > 0 && semaphore.tryAcquire()) {
            availableSeats -= 1;

            return new BookingResult(user, true, "Booking successful.");
        }
        return new BookingResult(user, false, "No seats available.");
    }
}
