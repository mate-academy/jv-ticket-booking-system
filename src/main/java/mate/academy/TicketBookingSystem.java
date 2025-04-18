package mate.academy;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class TicketBookingSystem {
    private final Semaphore seatsSemaphore;

    public TicketBookingSystem(int totalSeats) {
        this.seatsSemaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        try {
            // Очікує до 1 секунди, щоб забронювати квиток
            boolean acquired = seatsSemaphore.tryAcquire(1, TimeUnit.SECONDS);
            if (acquired) {
                return new BookingResult(user, true, "Booking successful.");
            } else {
                return new BookingResult(user, false, "No seats available.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return new BookingResult(user, false, "Booking interrupted.");
        }
    }

    public void cancelBooking(String user) {
        seatsSemaphore.release();
        System.out.println("Booking canceled for: " + user);
    }

    public int getAvailableSeats() {
        return seatsSemaphore.availablePermits();
    }
}
