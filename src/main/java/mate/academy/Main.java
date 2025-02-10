package mate.academy;

public class Main {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem(20);

        for (int i = 0; i < 25; i++) {
            new Thread(new MyThread(system)).start();
        }
    }
}
