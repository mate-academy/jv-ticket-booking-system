package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem implements Runnable {
    private final Semaphore seats;
    private final int totalSeats;

    public TicketBookingSystem(int totalSeats) {
        this.totalSeats = totalSeats;
        this.seats = new Semaphore(totalSeats, true);
    }

    public BookingResult attemptBooking(String user) {
        boolean acquired = seats.tryAcquire();
        if (acquired) {
            return new BookingResult(user, true, "Booking successful.");
        } else {
            return new BookingResult(user, false, "No seats available.");
        }

    }

    @Override
    public void run() {
        String user = Thread.currentThread().getName();
        BookingResult result = attemptBooking(user);
        System.out.println(result);
    }
}
