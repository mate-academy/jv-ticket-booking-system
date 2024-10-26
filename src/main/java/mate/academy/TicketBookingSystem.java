package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private static final String BOOKING_SUCCESS_MESSAGE = "Booking successful.";
    private static final String BOOKING_FAILURE_MESSAGE = "No seats available.";
    private final Semaphore totalSeatsLimit;

    public TicketBookingSystem(int totalSeats) {
        totalSeatsLimit = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        BookingResult bookingResult;
        try {
            if (totalSeatsLimit.tryAcquire()) {
                bookingResult = new BookingResult(user, true, BOOKING_SUCCESS_MESSAGE);
            } else {
                bookingResult = new BookingResult(user, false, BOOKING_FAILURE_MESSAGE);
            }
        } catch (Exception e) {
            throw new RuntimeException("The request has been interrupted " + e.getMessage());
        }
        return bookingResult;
    }
}
