package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private final Semaphore countSemaphore;
    private int availableSeats;

    public TicketBookingSystem(int totalSeats) {
        countSemaphore = new Semaphore(totalSeats, true);
        availableSeats = totalSeats;
    }

    public BookingResult attemptBooking(String user) {
        BookingResult bookingResult = null;
        try {
            countSemaphore.acquire();
            if (availableSeats == 0) {
                bookingResult = new BookingResult(user, false, "No seats available.");
                Thread.sleep(availableSeats);
            }
            if (availableSeats > 0) {
                availableSeats--;
                bookingResult = new BookingResult(user, true, "Booking successful.");
                Thread.sleep(availableSeats);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("The execution was interrupted");
        } finally {
            countSemaphore.release();
        }
        return bookingResult;
    }
}
