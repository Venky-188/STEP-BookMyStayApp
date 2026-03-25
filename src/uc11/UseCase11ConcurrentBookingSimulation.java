package uc11;

import java.util.*;

/**
 * Use Case 11: Concurrent Booking Simulation
 *
 * Demonstrates thread safety using synchronization.
 *
 * @author Venky
 * @version 11.0
 */
public class UseCase11ConcurrentBookingSimulation {

    // Shared inventory
    private static Map<String, Integer> inventory = new HashMap<>();

    static {
        inventory.put("Single", 2); // only 2 rooms available
    }

    /**
     * Thread-safe booking method
     */
    public static synchronized void bookRoom(String customerName, String roomType, int roomsRequested) {

        System.out.println(customerName + " is trying to book...");

        int available = inventory.getOrDefault(roomType, 0);

        if (roomsRequested <= available) {

            // Simulate delay (to expose race condition)
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            inventory.put(roomType, available - roomsRequested);

            System.out.println("Booking SUCCESS for " + customerName +
                    " | Remaining: " + inventory.get(roomType));

        } else {
            System.out.println("Booking FAILED for " + customerName +
                    " | Not enough rooms");
        }
    }

    /**
     * Booking Task (Thread)
     */
    static class BookingTask implements Runnable {

        private String customerName;

        public BookingTask(String customerName) {
            this.customerName = customerName;
        }

        @Override
        public void run() {
            bookRoom(customerName, "Single", 1);
        }
    }

    /**
     * Main method
     */
    public static void main(String[] args) {

        // Create multiple threads (simulating users)
        Thread t1 = new Thread(new BookingTask("Venky"));
        Thread t2 = new Thread(new BookingTask("Rahul"));
        Thread t3 = new Thread(new BookingTask("Anita"));

        // Start threads
        t1.start();
        t2.start();
        t3.start();
    }
}
