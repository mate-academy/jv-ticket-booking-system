package mate.academy;

import java.util.Random;

public class MyThread implements Runnable {
    private static final Random random = new Random();
    private static final ThreadLocal<String> user = ThreadLocal
            .withInitial(() -> "User" + random.nextInt(100));

    private final TicketBookingSystem bookingSystem;

    public MyThread(TicketBookingSystem bookingSystem) {
        this.bookingSystem = bookingSystem;
    }

    @Override
    public void run() {
        BookingResult result = bookingSystem.attemptBooking(user.get());

        System.out.println(Thread.currentThread()
                .getName() + " -> " + result.user() + ": " + result.message());

        if (result.success()) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                bookingSystem.releaseSeat();
                System.out.println(Thread.currentThread()
                        .getName() + " -> " + result.user() + " released a seat.");
            }
        }
    }
}
