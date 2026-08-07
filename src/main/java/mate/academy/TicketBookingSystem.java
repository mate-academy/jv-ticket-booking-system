package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private Semaphore semaphore;
    private int totalSeats;

    public TicketBookingSystem(int totalSeats) {
        this.totalSeats = totalSeats;
        semaphore = new Semaphore(this.totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        if (semaphore.tryAcquire()) {
            totalSeats--;
            return new BookingResult(user, true, "Booking successful.");
        } else {
            return new BookingResult(user, false, "No seats available.");
        }

    }
}
