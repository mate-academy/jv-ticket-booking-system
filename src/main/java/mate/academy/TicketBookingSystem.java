package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private static final String SUCCESS_MESSAGE = "Booking successful.";
    private static final String FAILURE_MESSAGE = "No seats available.";
    private final Semaphore availableSeats;

    public TicketBookingSystem(int totalSeats) {
        availableSeats = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        boolean booked = availableSeats.tryAcquire();
        String message = booked ? SUCCESS_MESSAGE : FAILURE_MESSAGE;
        return new BookingResult(user, booked, message);
    }
}

