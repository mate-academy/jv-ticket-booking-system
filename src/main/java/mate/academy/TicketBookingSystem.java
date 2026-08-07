package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private Semaphore semaphore;
    private int totalSeats;

    public TicketBookingSystem(int totalSeats) {
        semaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        BookingResult bookingResult1;
        try {
            semaphore.acquire();
            bookingResult1 = new BookingResult(user, true, "Booking successful.");
            totalSeats--;
            semaphore.release();
        } catch (InterruptedException e) {
            return new BookingResult(user, false, "No seats available.");
        }
        return bookingResult1;
    }
}
