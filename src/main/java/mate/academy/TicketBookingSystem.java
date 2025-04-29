package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {

    private final Semaphore seats;

    public TicketBookingSystem(int totalSeats) {
        this.seats = new Semaphore(totalSeats, true);
    }

    public BookingResult attemptBooking(String user) {
        boolean acquired = seats.tryAcquire();

        if (acquired) {
            return new BookingResult(
                user,
                true,
                "Booking successful."
            );
        }

        return new BookingResult(
            user,
            false,
            "No seats available."
        );
    }
}
