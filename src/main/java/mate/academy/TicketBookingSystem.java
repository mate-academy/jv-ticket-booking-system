package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {
        this.semaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        boolean success = false;
        String message = "No seats available.";
        try {
            if (semaphore.tryAcquire()) { // Проверяем, доступно ли место
                success = true;
                message = "Booking successful.";
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new BookingResult(user, success, message);
    }

}
