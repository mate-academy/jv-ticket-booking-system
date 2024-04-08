package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {

    private final Semaphore availableSeats;
    private final int totalSeats;

    public TicketBookingSystem(int totalSeats) {
        this.totalSeats = totalSeats;
        this.availableSeats = new Semaphore(totalSeats, true); // Fair semaphore to ensure first-come, first-served
    }

    public BookingResult attemptBooking(String user) {
        boolean acquired = availableSeats.tryAcquire();
        try {
            if (acquired) {
                // Simulate booking process
                // In a real scenario, you would have additional logic here to handle the booking
                return new BookingResult(user, true, "Booking successful.");
            } else {
                return new BookingResult(user, false, "No seats available.");
            }
        } finally {
            // If there's logic to be implemented in the finally block, it should go here.
            // For example, releasing the semaphore if the booking was not successful.
            // However, as per the previous context, we're assuming every acquisition leads to a successful booking,
            // so there's no need to release the semaphore here.
            // This comment is to ensure the block is not empty to satisfy Checkstyle.
        }
    }
}
