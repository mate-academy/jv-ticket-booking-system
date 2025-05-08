package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private final Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {
        this.semaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        boolean acquired = false;
        String message;
        try {
            acquired = semaphore.tryAcquire();
            if (acquired) {
                message = "Booking successful.";
            } else {
                message = "No seats available.";
            }
        } catch (Exception e) {
            message = "Booking interrupted for user: " + user;
        }
        return new BookingResult(user, acquired, message);
    }
}
