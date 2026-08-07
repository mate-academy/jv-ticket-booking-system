package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {

    private final Semaphore seats;

    public TicketBookingSystem(int totalSeats) {
        this.seats = new Semaphore(totalSeats, true);
    }

    public BookingResult attemptBooking(String user) {

        boolean seatIsAcquired = seats.tryAcquire();

        if (!seatIsAcquired) {
            return new BookingResult(user, false, "No seats available.");
        }

        return new BookingResult(user, true, "Booking successful.");
    }
}
