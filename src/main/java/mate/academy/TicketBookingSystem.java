package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private Semaphore semaphore;
    private volatile Integer totalSeats;

    public TicketBookingSystem(int totalSeats) {

        this.semaphore = new Semaphore(totalSeats);
        this.totalSeats = totalSeats;
    }

    public BookingResult attemptBooking(String user) {
        BookingResult result;
        try {
            semaphore.acquire();
            synchronized (totalSeats) {
                if (totalSeats > 0) {
                    totalSeats--;
                    result = new BookingResult(user, true, "Booking successful.");
                } else {
                    result = new BookingResult(user, false, "No seats available.");
                }
            }
            semaphore.release();
        } catch (InterruptedException e) {
            result = new BookingResult(user, false, "Failed to enter site");
        }
        return result;
    }
}
