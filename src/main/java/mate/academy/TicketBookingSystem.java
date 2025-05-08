package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private final Semaphore semaphore;
    private int seats;

    public TicketBookingSystem(int totalSeats) {
        this.seats = totalSeats;
        this.semaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        try {
            semaphore.acquire();
            synchronized (this) {
                if (seats > 0) {
                    seats--;
                    return new BookingResult(user, true, "Booking successful.");
                } else {
                    return new BookingResult(user, false, "No seats available.");
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
        }
    }
}
