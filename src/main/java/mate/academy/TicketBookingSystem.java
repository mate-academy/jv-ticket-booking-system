package mate.academy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private final Semaphore semaphore;
    private List<String> users;
    private int tickets;

    public TicketBookingSystem(int totalSeats) {
        semaphore = new Semaphore(totalSeats);
        users = new ArrayList<>(totalSeats);
        tickets = totalSeats;
    }

    public BookingResult attemptBooking(String user) {
        BookingResult result;
        if (semaphore.availablePermits() > 0 && users.size() < tickets) {
            try {
                semaphore.acquire();
                Thread.currentThread().sleep(600);
                result = new BookingResult(user, true, "Booking successful.");
                users.add(user);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                semaphore.release();
            }
        } else {
            result = new BookingResult(user, false, "No seats available.");
        }
        return result;
    }
}
