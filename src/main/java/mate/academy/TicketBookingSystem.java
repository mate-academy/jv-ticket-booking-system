package mate.academy;

public class TicketBookingSystem {
    private volatile int totalSeats;

    public TicketBookingSystem(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public synchronized BookingResult attemptBooking(String user) {
        if (totalSeats <= 0) {
            return new BookingResult(user, false, "No seats available.");
        }
        --totalSeats;
        return new BookingResult(user, true, "Booking successful.");
    }
}
