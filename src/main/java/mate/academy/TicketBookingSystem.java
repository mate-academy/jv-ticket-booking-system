package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private Semaphore semaphore;
    private int seatsAvailable;

    public TicketBookingSystem(int totalSeats) {
        semaphore = new Semaphore(totalSeats);
        seatsAvailable = totalSeats;
    }

    public BookingResult attemptBooking(String user) {    
        try {
            semaphore.acquire();
            if (seatsAvailable > 0) {
                decrementSeatsAvailable();
                return new BookingResult(user, true, "Booking successful.");
            } else {
                return new BookingResult(user, false, "No seats available.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread was interrupted!", e);
        } finally {
            semaphore.release();
        }
    }

    private synchronized void decrementSeatsAvailable() {
        seatsAvailable--;
    }
}
