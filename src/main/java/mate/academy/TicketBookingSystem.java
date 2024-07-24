package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {

    private int totalSeats;
    private Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {
        this.totalSeats = totalSeats;
        this.semaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        try {
            semaphore.acquire();

            boolean bookingSuccess = performBooking(user);

            if (bookingSuccess) {
                return new BookingResult(user, true, "Booking successful.");
            } else {
                return new BookingResult(user, false, "No seats available.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return new BookingResult(user, false, "Booking interrupted");
        } finally {
            semaphore.release();
        }
    }

    private boolean performBooking(String user) {
        if (totalSeats > 0) {
            totalSeats--;
            return true;
        } else {
            return false;
        }
    }
}

