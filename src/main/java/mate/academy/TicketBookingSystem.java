package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private final Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {
        this.semaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        if (semaphore.tryAcquire()) {
            try {
                return new BookingResult(user, true, "Booking successful.");
            } finally {
                System.out.println("Lets watch the movie)");
            }
        } else {
            return new BookingResult(user, false, "No seats available.");
        }
    }
}
