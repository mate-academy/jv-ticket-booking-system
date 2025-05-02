package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {

    private final Semaphore seats;

    public TicketBookingSystem(int totalSeats) {
        this.seats = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        boolean gotSeats = seats.tryAcquire();
        while (!gotSeats) {
            return new BookingResult(user, false, "No seats available.");
        }
        int seatsLeft = seats.availablePermits();
        String message = "Booking successful.";
        return new BookingResult(user, true, message);
    }
}
