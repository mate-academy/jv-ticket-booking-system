package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private final Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {
        semaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        boolean success = false;
        String message = "No seats available.";
        if (semaphore.tryAcquire()) {
            try {
                success = true;
                message = "Booking successful.";
            } finally {
                System.out.println(message);
            }
        }
        return new BookingResult(user, success, message);
    }
}
