package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem extends Thread {
    private final Semaphore semaphore;
    private int totalSeats;

    public TicketBookingSystem(int totalSeats) {
        this.totalSeats = totalSeats;
        this.semaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        try {
            synchronized (this) {
                semaphore.acquire();
                if (totalSeats > 0) {
                    totalSeats--;
                    semaphore.release();
                    return new BookingResult(user, true, "Booking successful.");
                } else {
                    semaphore.release();
                    return new BookingResult(user, false, "No seats available.");
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
