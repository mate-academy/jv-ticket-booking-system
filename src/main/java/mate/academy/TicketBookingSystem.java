package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private int totalSeats;
    private final Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {
        this.totalSeats = totalSeats;
        this.semaphore = new Semaphore(totalSeats);

    }

    public BookingResult attemptBooking(String user) {
        BookingResult bookingResult = null;
        try {
            semaphore.acquire();
            synchronized (this) {
                if (this.totalSeats >= 1) {

                    bookingResult = new BookingResult(user, true, "Booking successful.");
                    this.totalSeats--;
                } else {
                    bookingResult = new BookingResult(user, false, "No seats available.");
                }
                semaphore.release();
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
        return bookingResult;
    }
}
