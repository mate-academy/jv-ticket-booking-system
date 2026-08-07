package mate.academy;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TicketBookingSystem {
    private final Lock lock;
    private final Condition seatsAvailable;
    private int totalSeats;
    private int successfulBookings;

    public TicketBookingSystem(int totalSeats) {
        this.lock = new ReentrantLock(true);
        this.seatsAvailable = lock.newCondition();
        this.totalSeats = totalSeats;
        this.successfulBookings = 0;
    }

    public BookingResult attemptBooking(String user) {
        lock.lock();
        try {
            System.out.println(user + " acquired the lock");
            if (successfulBookings < totalSeats) {
                successfulBookings++;
                System.out.println(user + " booked a seat");
                return new BookingResult(true, "Booking successful.", user);
            } else {
                System.out.println(user + " couldn't book a seat");
                return new BookingResult(false, "No seats available.", user);
            }
        } finally {
            lock.unlock();
            System.out.println(user + " is releasing the lock");
        }
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getSuccessfulBookings() {
        return successfulBookings;
    }
}
