package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private static int availableSeats;
    private final Semaphore semaphore = new Semaphore(1);

    public TicketBookingSystem(int totalSeats) {
        availableSeats = totalSeats;
    }

    public BookingResult attemptBooking(String user) {
        try {
            semaphore.acquire();
            if (availableSeats > 0) {
                availableSeats--;
                semaphore.release();
                return new BookingResult(user, true,"Booking successful.");
            }
            semaphore.release();
            return new BookingResult(user,false,"No seats available.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
