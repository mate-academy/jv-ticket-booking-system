package mate.academy;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class TicketBookingSystem {
    private static final long BOOKING_TIMEOUT = 5; // seconds
    private final Semaphore semaphore;
    private final ConcurrentHashMap<String, Integer> bookedSeats;
    private final int totalSeats;

    public TicketBookingSystem(int totalSeats) {
        if (totalSeats <= 0) {
            throw new IllegalArgumentException("Total seats must be positive");
        }
        this.totalSeats = totalSeats;
        this.semaphore = new Semaphore(totalSeats, true);
        this.bookedSeats = new ConcurrentHashMap<>();
    }

    public BookingResult attemptBooking(String user) {
        if (user == null || user.trim().isEmpty()) {
            return new BookingResult(user, false, "Invalid user information.");
        }

        try {
            // Try acquiring a permit with timeout
            if (semaphore.tryAcquire(BOOKING_TIMEOUT, TimeUnit.SECONDS)) {
                try {
                    // Simulate booking process
                    processBooking(user);

                    // Record the booking
                    int seatNumber = assignSeatNumber();
                    bookedSeats.put(user, seatNumber);

                    return new BookingResult(user, true,
                            "Booking successful.");
                } catch (Exception e) {
                    semaphore.release(); // Release permit if booking fails
                    return new BookingResult(user, false,
                            "Booking failed: " + e.getMessage());
                }
            } else {
                return new BookingResult(user, false,
                        "No seats available.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return new BookingResult(user, false,
                    "Booking interrupted.");
        }
    }

    private void processBooking(String user) throws InterruptedException {
        System.out.printf("Processing booking for %s...%n", user);
        // Simulate various booking steps
        Thread.sleep(1000);
    }

    private synchronized int assignSeatNumber() {
        // Simple seat assignment strategy
        return totalSeats - semaphore.availablePermits() + 1;
    }

    public int getAvailableSeats() {
        return semaphore.availablePermits();
    }

    public boolean cancelBooking(String user) {
        Integer seatNumber = bookedSeats.remove(user);
        if (seatNumber != null) {
            semaphore.release();
            System.out.printf("Booking cancelled for user %s, seat %d%n",
                    user, seatNumber);
            return true;
        }
        return false;
    }

    public Integer getUserSeat(String user) {
        return bookedSeats.get(user);
    }
}
