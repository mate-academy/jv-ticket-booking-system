package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private final Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {
        this.semaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        if (semaphore.tryAcquire()) {
            return new BookingResult(user, true, "Successfully booked a seat.");
        } else {
            return new BookingResult(user, false, "No available seats. Try again later.");
        }
    }

    public void releaseSeat() {
        semaphore.release();
    }

}
