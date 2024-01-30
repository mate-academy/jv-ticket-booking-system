package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private final Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {
        this.semaphore = new Semaphore(totalSeats, true);
    }

    public BookingResult attemptBooking(String user) {
        BookingResult var3;
        try {
            this.semaphore.acquire();
            BookingResult var2 = new BookingResult(user, true, "Booking successful.");
            return var2;
        } catch (InterruptedException var7) {
            Thread.currentThread().interrupt();
            var3 = new BookingResult(user, false, "Booking interrupted.");
        } finally {
            this.semaphore.release();
        }

        return var3;
    }
}
