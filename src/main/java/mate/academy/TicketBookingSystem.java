package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private final Semaphore seatsSemaphore;
    private int availableSeats;

    public TicketBookingSystem(int totalSeats) {
        this.availableSeats = totalSeats;
        this.seatsSemaphore = new Semaphore(totalSeats, true);
    }

    public BookingResult attemptBooking(String user) {
        if (seatsSemaphore.tryAcquire()) {
            synchronized (this) {
                if (availableSeats > 0) {
                    availableSeats--;
                    return new BookingResult(user, true, "Booking successful.");
                } else {
                    return new BookingResult(user, false, "No seats available.");
                }
            }
        } else {
            return new BookingResult(user, false, "No seats available.");
        }
    }
}
