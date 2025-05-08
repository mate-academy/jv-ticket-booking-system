package mate.academy;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class TicketBookingSystem extends Thread {
    private AtomicInteger availableSeats;
    private Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {
        availableSeats = new AtomicInteger(totalSeats);
        semaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        boolean booked = false;
        try {
            semaphore.acquire();
            int seats = availableSeats.get();
            if (seats > 0) {
                availableSeats.decrementAndGet();
                booked = true;
                return new BookingResult(user, true,
                        "Booking successful.");
            } else {
                return new BookingResult(user, false,
                        "No seats available.");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
        }
    }
}
