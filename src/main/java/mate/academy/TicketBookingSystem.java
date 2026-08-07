package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private final Semaphore totalSeats;
    private int availableSeats;

    public TicketBookingSystem(int totalSeats) {
        this.totalSeats = new Semaphore(1);
        this.availableSeats = totalSeats;
    }

    public BookingResult attemptBooking(String user) {
        try {
            totalSeats.acquire();
            if (availableSeats > 0) {
                availableSeats--;
                totalSeats.release();
                return getSuccessfulBookingResult(user);
            } else {
                totalSeats.release();
                return getUnsuccessfulBookingResult(user);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private BookingResult getSuccessfulBookingResult(String user) {
        return new BookingResult(user, true, "Booking successful.");
    }

    private BookingResult getUnsuccessfulBookingResult(String user) {
        return new BookingResult(user, false, "No seats available.");
    }

}
