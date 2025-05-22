package mate.academy;

public class CustomThread implements Runnable {
    private final TicketBookingSystem bookingSystem;

    public CustomThread(TicketBookingSystem bookingSystem) {
        this.bookingSystem = bookingSystem;
    }

    @Override
    public void run() {
        try {
            bookingSystem.attemptBooking("User" + Thread.currentThread().getId());
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
