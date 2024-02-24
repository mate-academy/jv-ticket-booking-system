package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    int totalSeats;
    Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {
        this.totalSeats = totalSeats;
        this.semaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user)  {
        try {
            semaphore.acquire();
            if(totalSeats > 0) {
                synchronized (this) {
                    totalSeats--;
                }
                return new BookingResult(user, true, "Booking successful.");
            } else {
                return new BookingResult(user, false, "No seats available.");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
        }
    }
}
