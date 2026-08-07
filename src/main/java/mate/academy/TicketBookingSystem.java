package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private static volatile int seatsLeft;
    private final Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {
        this.semaphore = new Semaphore(totalSeats);
        TicketBookingSystem.seatsLeft = totalSeats;
    }

    public BookingResult attemptBooking(String user) {
        BookingResult bookingResult = new BookingResult(user, false, "No seats available.");
        try {
            semaphore.acquire();
            if (seatsLeft > 0) {
                synchronized (TicketBookingSystem.class) {
                    TicketBookingSystem.seatsLeft--;
                }
                bookingResult = new BookingResult(user, true, "Booking successful.");
            }
            semaphore.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return bookingResult;
    }
}
