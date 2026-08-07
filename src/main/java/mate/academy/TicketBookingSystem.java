package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private static final String SUCCESS_MESSAGE = "Booking successful.";
    private static final String FAILURE_MESSAGE = "No seats available.";
    private final Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {
        // Додаємо true для "чесності", щоб люди в черзі проходили по порядку
        this.semaphore = new Semaphore(totalSeats, true);
    }

    public BookingResult attemptBooking(String user) {
        if (semaphore.tryAcquire()) {
            return new BookingResult(user, true, SUCCESS_MESSAGE);
        }
        return new BookingResult(user, false, FAILURE_MESSAGE);
    }
}
