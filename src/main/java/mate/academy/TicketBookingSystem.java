package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private int totalSeats;
    private final Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {
        this.totalSeats = totalSeats;
        this.semaphore = new Semaphore(totalSeats, true);
    }

    public BookingResult attemptBooking(String user) {
        try {
            if (!semaphore.tryAcquire()) {
                return new BookingResult(user, false, "No seats available.");
            }

            synchronized (this) {
                if (totalSeats > 0) {
                    totalSeats--;
                    return new BookingResult(user, true, "Booking successful.");
                } else {
                    return new BookingResult(user, false, "No seats available.");
                }
            }
        } catch (Exception e) {
            return new BookingResult(user, false, "Something went wrong.");
        }
    }
}
