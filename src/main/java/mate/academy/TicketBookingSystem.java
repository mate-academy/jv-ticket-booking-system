package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private final Semaphore semaphore;
    private int availableSeats;

    public TicketBookingSystem(int totalSeats) {
        this.semaphore = new Semaphore(totalSeats, true);
        this.availableSeats = totalSeats;
    }

    public BookingResult attemptBooking(String user) {
        BookingResult bookingResult;
        if (availableSeats > 0) {
            try {
                semaphore.acquire();
                availableSeats--;
                Thread.sleep(100);
                bookingResult = new BookingResult(user, true, "Booking successful.");
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
            semaphore.release();
            return bookingResult;
        }
        return new BookingResult(user, false, "No seats available.");
    }
}
