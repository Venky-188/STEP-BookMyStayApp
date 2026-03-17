package uc5;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Use Case 5: Booking Request Queue (FIFO)
 */
public class UseCase5BookingRequestQueue {

    public static void main(String[] args) {

        // Create Queue
        Queue<Reservation> bookingQueue = new LinkedList<>();

        // Add booking requests (FIFO order)
        bookingQueue.add(new Reservation("Venky", "Single", 2));
        bookingQueue.add(new Reservation("Rahul", "Suite", 1));
        bookingQueue.add(new Reservation("Anita", "Double", 1));

        System.out.println("Booking Requests in Queue:\n");

        // Display queue (NO removal)
        for (Reservation r : bookingQueue) {
            System.out.println("Customer: " + r.getCustomerName());
            System.out.println("Room Type: " + r.getRoomType());
            System.out.println("Rooms Requested: " + r.getRoomsRequested());
            System.out.println("----------------------");
        }

        System.out.println("\nNext request to process (FIFO):");

        // Peek first request (without removing)
        Reservation next = bookingQueue.peek();

        if (next != null) {
            System.out.println("Customer: " + next.getCustomerName());
            System.out.println("Room Type: " + next.getRoomType());
        }
    }
}