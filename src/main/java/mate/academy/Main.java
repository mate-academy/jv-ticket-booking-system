package mate.academy;

public class Main {
    public static void main(String[] args) {
        TicketBookingSystem bookingSystem = new TicketBookingSystem(50);

        for (int i = 0; i < 70; i++) {
            Thread thread = new Thread(new CustomThread(bookingSystem));
            thread.start();
        }
    }
}
