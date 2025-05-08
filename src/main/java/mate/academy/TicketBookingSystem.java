package mate.academy;

public class TicketBookingSystem {
    private int seatsAvailable;

    public TicketBookingSystem(int totalSeats) {
        this.seatsAvailable = totalSeats;
    }

    public synchronized BookingResult attemptBooking(String user) {
        if (seatsAvailable > 0) {
            seatsAvailable--;
            return new BookingResult(user, true, "Booking successful.");
        } else {
            return new BookingResult(user, false, "No seats available.");
        }
    }
}
