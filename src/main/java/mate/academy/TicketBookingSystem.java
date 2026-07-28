package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private Semaphore seats;

    public TicketBookingSystem(int totalSeats) {
        seats = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        boolean booked = seats.tryAcquire();
        return booked
                ? new BookingResult(user, booked, "Booking successful.")
                : new BookingResult(user, booked, "No seats available.");
    }
}

