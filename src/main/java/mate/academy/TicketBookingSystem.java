package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {

    private final Semaphore availableSeats;
    private final int totalSeats;

    public TicketBookingSystem(int totalSeats) {
        this.totalSeats = totalSeats;
        this.availableSeats = new Semaphore(totalSeats, true);
    }

    public BookingResult attemptBooking(String user) {
        boolean acquired = availableSeats.tryAcquire();
        try {
            if (acquired) {

                
                return new BookingResult(user, true, "Booking successful.");
            } else {
                return new BookingResult(user, false, "No seats available.");
            }
        } finally {
            if (acquired) {

            }
        }
    }
}
