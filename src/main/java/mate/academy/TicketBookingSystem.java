package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private final Semaphore seatSemaphore;

    public TicketBookingSystem(int totalSeats) {
        this.seatSemaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        if (seatSemaphore.tryAcquire()) {
            return new BookingResult(user, true, "Booking successful");
        } else {
            return new BookingResult(user, false, "Booking failed, no seats available");
        }
    }
}
