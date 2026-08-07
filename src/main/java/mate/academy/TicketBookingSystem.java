package mate.academy;

import java.util.concurrent.atomic.AtomicInteger;

public class TicketBookingSystem {
    private final int totalSeats;
    private final AtomicInteger availableSeats;

    public TicketBookingSystem(int totalSeats) {
        this.totalSeats = totalSeats;
        this.availableSeats = new AtomicInteger(totalSeats); // Thread-safe counter
    }

    public BookingResult attemptBooking(String user) {
        while (true) {
            int currentAvailableSeats = availableSeats.get();
            if (currentAvailableSeats <= 0) {
                return new BookingResult(user, false, "No seats available.");
            }
            if (availableSeats.compareAndSet(currentAvailableSeats, currentAvailableSeats - 1)) {
                return new BookingResult(user, true, "Booking successful.");
            }
        }
    }
}
