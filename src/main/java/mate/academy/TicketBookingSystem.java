package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private static final String NO_AVAILABLE = "No seats available.";
    private static final String AVAILABLE = "Booking successful.";
    private Semaphore semaphore;
    private int totalSeats;

    public TicketBookingSystem(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public BookingResult attemptBooking(String user) {
        synchronized (this) {
            if (totalSeats <= 0) {
                return new BookingResult(user, false, NO_AVAILABLE);
            } else {
                try {
                    semaphore = new Semaphore(totalSeats);
                    totalSeats--;
                    semaphore.acquire();
                    return new BookingResult(user, true, AVAILABLE);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                } finally {
                    semaphore.release();
                }
            }
        }
    }
}
