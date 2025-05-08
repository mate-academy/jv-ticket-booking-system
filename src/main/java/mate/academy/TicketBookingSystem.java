package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private static final String SUCCESSFUL_BOOKING_OPERATION = "Booking successful.";
    private static final String FAILED_BOOKING_OPERATION = "No seats available.";
    private final Semaphore semaphore;
    private int seatsCount;

    public TicketBookingSystem(int totalSeats) {
        semaphore = new Semaphore(totalSeats);
        seatsCount = totalSeats;
    }

    public BookingResult attemptBooking(String user) {
        try {
            if (semaphore.tryAcquire()) {
                synchronized (this) {
                    if (seatsCount > 0) {
                        seatsCount--;
                        return new BookingResult(user, true, SUCCESSFUL_BOOKING_OPERATION);
                    }
                }
            }
            return new BookingResult(user, false, FAILED_BOOKING_OPERATION);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
