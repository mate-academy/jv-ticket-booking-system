package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private final Semaphore seatSemaphore;

    public TicketBookingSystem(int totalSeats) {
        this.seatSemaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        if (seatSemaphore.tryAcquire()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return new BookingResult(user, false, "Booking interrupted.");
            }
            return new BookingResult(user, true, "Booking successful.");
        } else {
            return new BookingResult(user, false, "No seats available.");
        }
    }

}
