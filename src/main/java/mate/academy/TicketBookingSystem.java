package mate.academy;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class TicketBookingSystem {

    private final Semaphore semaphore;

    private final AtomicInteger seatsAvailable;

    public TicketBookingSystem(int totalSeats) {
        semaphore = new Semaphore(totalSeats);
        seatsAvailable = new AtomicInteger(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        if (seatsAvailable.get() == 0) {
            return new BookingResult(user, false, "No seats available.");
        }

        try {
            semaphore.acquire();
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            return new BookingResult(user, false, "Booking failed");
        }

        BookingResult bookingResult = new BookingResult(user, true, "Booking successful.");

        seatsAvailable.getAndDecrement();

        semaphore.release();

        return bookingResult;
    }
}
