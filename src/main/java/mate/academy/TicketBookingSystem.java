package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private final Semaphore semaphore = new Semaphore(1);
    private int seats;

    public TicketBookingSystem(int totalSeats) {
        this.seats = totalSeats;
    }

    public BookingResult attemptBooking(String user) {
        try {
            semaphore.acquire();
            if (seats <= 0) {
                semaphore.release();
                return new BookingResult(user, false, "No seats available.");
            }
            seats--;
            semaphore.release();
            return new BookingResult(user, true, "Booking successful.");
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted.");
        }
        return null;
    }
}
