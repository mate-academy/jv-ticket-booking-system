package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private static Semaphore semaphone;

    public TicketBookingSystem(int totalSeats) {
        semaphone = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        if (semaphone.tryAcquire()) {
            return new BookingResult(user, true, "Booking successful.");
        }
        return new BookingResult(user, false, "No seats available.");
    }
}
