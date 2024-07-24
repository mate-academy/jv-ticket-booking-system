package mate.academy;

public class Main {
    public static void main(String[] args) {
        TicketBookingSystem bookingSystem = new TicketBookingSystem(350);

        for (int i = 1; i <= 3000; i++) {
            int userNumber = i;
            new Thread(() -> {
                BookingResult result = bookingSystem.attemptBooking("User" + userNumber);
                System.out.println(result);
            }).start();
        }
    }
}
