package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private final Semaphore semaphore;

    // Ініціалізація системи з кількістю доступних місць
    public TicketBookingSystem(int totalSeats) {
        this.semaphore = new Semaphore(totalSeats);
    }

    // Спроба забронювати квиток
    public BookingResult attemptBooking(String user) {
        boolean success = false;
        String message;

        try {
            // Спроба отримати місце (захоплення семафора)
            if (semaphore.tryAcquire()) {
                // Якщо місце є, бронювання успішне
                success = true;
                message = "Booking successful.";
            } else {
                // Якщо місць немає, бронювання не вдалося
                message = "No seats available.";
            }
        } catch (Exception e) {
            message = "An error occurred while processing booking for " + user;
        }

        return new BookingResult(user, success, message);
    }
}
