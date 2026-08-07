package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {

    private Semaphore semaphore;

    private int availableSeats;

    public TicketBookingSystem(int totalSeats) {
        availableSeats = totalSeats;
        semaphore = new Semaphore(availableSeats);
    }

    public BookingResult attemptBooking(String user) {
        if (semaphore.tryAcquire()) {
            availableSeats--;
            return new BookingResult(user, true, "Booking successful.");
        }
        return new BookingResult(user, false, "No seats available.");
    }
}
