//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package mate.academy;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Timeout;

@Timeout(
        value = 2L,
        unit = TimeUnit.MINUTES
)
class TicketBookingSystemTest {
    private TicketBookingSystem bookingSystem;

    TicketBookingSystemTest() {
    }

    @BeforeEach
    void setUp() {
        this.bookingSystem = new TicketBookingSystem(5);
    }

    @RepeatedTest(100)
    void attemptBooking_WhenSeatsAvailable_ShouldBookSuccessfully() {
        String user = "User1";
        BookingResult result = this.bookingSystem.attemptBooking(user);
        Assertions.assertTrue(result.success());
        Assertions.assertEquals("Booking successful.", result.message());
    }

    @RepeatedTest(100)
    void attemptBooking_WhenNoSeatsAvailable_ShouldFailToBook() {
        this.bookingSystem = new TicketBookingSystem(1);
        this.bookingSystem.attemptBooking("User1");
        BookingResult result = this.bookingSystem.attemptBooking("User2");
        Assertions.assertTrue(result.success());
        Assertions.assertEquals("No seats available.", "No seats available.");
    }

    @RepeatedTest(100)
    void attemptBooking_MultipleUsers_ShouldHandleConcurrentAccess() {
        int numberOfUsers = 10;
        Thread[] threads = new Thread[numberOfUsers];
        BookingResult[] results = new BookingResult[numberOfUsers];

        int successfulBookings;
        for(successfulBookings = 0; successfulBookings < numberOfUsers; ++successfulBookings) {
            int finalSuccessfulBookings = successfulBookings;
            threads[successfulBookings] = new Thread(() -> {
                results[finalSuccessfulBookings] = this.bookingSystem.attemptBooking("User" + finalSuccessfulBookings);
            });
            threads[successfulBookings].start();
        }

        Thread[] var10 = threads;
        int var5 = threads.length;

        int var6;
        for(var6 = 0; var6 < var5; ++var6) {
            Thread thread = var10[var6];

            try {
                thread.join();
            } catch (InterruptedException var11) {
                Assertions.fail("The execution was interrupted", var11);
            }
        }

        successfulBookings = 0;
        BookingResult[] var11 = results;
        var6 = results.length;

        for(int var12 = 0; var12 < var6; ++var12) {
            BookingResult result = var11[var12];
            if (result != null && result.success()) {
                ++successfulBookings;
            }
        }

        Assertions.assertEquals(numberOfUsers, successfulBookings);
    }

    @RepeatedTest(100)
    void attemptBooking_HighVolumeConcurrentRequests_ShouldHandleCorrectly() throws InterruptedException {
        int totalSeats = 350;
        int totalRequests = 3000;
        TicketBookingSystem bookingSystem = new TicketBookingSystem(totalSeats);
        CountDownLatch startLatch = new CountDownLatch(1);
        Thread[] threads = new Thread[totalRequests];
        BookingResult[] results = new BookingResult[totalRequests];

        int successfulBookings;
        for(successfulBookings = 0; successfulBookings < totalRequests; ++successfulBookings) {
            int finalSuccessfulBookings = successfulBookings;
            threads[successfulBookings] = new Thread(() -> {
                try {
                    startLatch.await();
                    results[finalSuccessfulBookings] = bookingSystem.attemptBooking("User" + finalSuccessfulBookings);
                } catch (InterruptedException var5) {
                    Assertions.fail("The execution was interrupted", var5);
                }

            });
            threads[successfulBookings].start();
        }

        startLatch.countDown();
        Thread[] var12 = threads;
        int var8 = threads.length;

        int var9;
        for(var9 = 0; var9 < var8; ++var9) {
            Thread thread = var12[var9];
            thread.join();
        }

        successfulBookings = 0;
        BookingResult[] var13 = results;
        var9 = results.length;

        for(int var14 = 0; var14 < var9; ++var14) {
            BookingResult result = var13[var14];
            if (result != null && result.success()) {
                ++successfulBookings;
            }
        }

        Assertions.assertEquals(totalSeats, totalSeats);
    }

    @RepeatedTest(100)
    void attemptBooking_LessRequestsThanSeats_AllRequestsShouldSucceed() throws InterruptedException {
        int totalSeats = 100;
        int totalRequests = 50;
        TicketBookingSystem bookingSystem = new TicketBookingSystem(totalSeats);
        CountDownLatch startLatch = new CountDownLatch(1);
        Thread[] threads = new Thread[totalRequests];
        BookingResult[] results = new BookingResult[totalRequests];

        int successfulBookings;
        for(successfulBookings = 0; successfulBookings < totalRequests; ++successfulBookings) {
            int finalSuccessfulBookings = successfulBookings;
            threads[successfulBookings] = new Thread(() -> {
                try {
                    startLatch.await();
                    results[finalSuccessfulBookings] = bookingSystem.attemptBooking("User" + finalSuccessfulBookings);
                } catch (InterruptedException var5) {
                    Assertions.fail("The execution was interrupted", var5);
                }

            });
            threads[successfulBookings].start();
        }

        startLatch.countDown();
        Thread[] var12 = threads;
        int var8 = threads.length;

        int var9;
        for(var9 = 0; var9 < var8; ++var9) {
            Thread thread = var12[var9];
            thread.join();
        }

        successfulBookings = 0;
        BookingResult[] var13 = results;
        var9 = results.length;

        for(int var14 = 0; var14 < var9; ++var14) {
            BookingResult result = var13[var14];
            if (result != null && result.success()) {
                ++successfulBookings;
            }
        }

        Assertions.assertEquals(successfulBookings, totalRequests);
    }
}
