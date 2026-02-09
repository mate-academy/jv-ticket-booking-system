package mate.academy;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class TicketBookingSystem {

    private final Semaphore seats;
    private final AtomicInteger booked = new AtomicInteger(0);
    private final int totalSeats;

    public TicketBookingSystem(int totalSeats) {
        if (totalSeats < 0) {
            throw new IllegalArgumentException("totalSeats must be >= 0");
        }
        this.totalSeats = totalSeats;
        this.seats = new Semaphore(totalSeats, true);
    }

    public BookingResult attemptBooking(String user) {
        if (user == null || user.isBlank()) {
            return new BookingResult(String.valueOf(user), false, "Invalid user");
        }

        boolean acquired = seats.tryAcquire();
        if (!acquired) {
            return new BookingResult(user, false, "No seats available.");
        }

        int currentBooked = booked.incrementAndGet();
        int remaining = seats.availablePermits();

        return new BookingResult(
                user,
                true,
                "Booking successful."
        );
    }
}
