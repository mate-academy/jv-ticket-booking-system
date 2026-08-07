package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem implements Runnable {

    private final int totalSeats;
    private Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {
        this.totalSeats = totalSeats;
        this.semaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        boolean acquired = semaphore.tryAcquire();

        if (acquired) {
            return new BookingResult(user, true, "Booking successful.");
        } else {
            return new BookingResult(user, false, "No seats available.");
        }
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        BookingResult result = attemptBooking(threadName);
        System.out.println(result.message());
    }
}
