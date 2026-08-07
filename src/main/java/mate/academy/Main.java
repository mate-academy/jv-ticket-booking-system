package mate.academy;

public class Main {
    public static void main(String[] args) {
        int totalSeats = 5;
        TicketBookingSystem system = new TicketBookingSystem(totalSeats);

        for (int i = 1; i <= 10; i++) {
            String user = "User" + i;
            Thread thread = new Thread(() -> {
                BookingResult result = system.attemptBooking(user);
                System.out.println(result.message());
            });
            thread.start();
        }
    }
}
