package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private final Semaphore semaphore;

    public TicketBookingSystem(int totalSeats) {
        this.semaphore = new Semaphore(totalSeats);
    }

    public BookingResult attemptBooking(String user) {
        //System.out.println(user + "is trying to book a ticket");
        boolean success = semaphore.tryAcquire();

        if (success) {
            //System.out.println(user + "booked a ticket");
            return new BookingResult(user, true, "Booking successful.");
        } else {
            //System.out.println(user + " could not book a ticket. No seats available.");
            return new BookingResult(user, false, "No seats available.");
        }
    }
}
