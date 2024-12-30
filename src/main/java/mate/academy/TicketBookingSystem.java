package mate.academy;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class TicketBookingSystem {
    private AtomicInteger availableSeats;
    private final Semaphore hall;

    public TicketBookingSystem(int totalSeats) {
        this.hall = new Semaphore(totalSeats);
        this.availableSeats = new AtomicInteger(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        try {
            hall.acquire();
            if (availableSeats.get() > 0) {
                BookingResult ticket =
                        new BookingResult(user, true, "Booking successful.");
                availableSeats.decrementAndGet();
                hall.release();
                return ticket;
            } else {
                BookingResult noTickets =
                        new BookingResult(user, false, "No seats available.");
                hall.release();
                return noTickets;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            hall.release();
            throw new RuntimeException(e);
        }
    }
}
