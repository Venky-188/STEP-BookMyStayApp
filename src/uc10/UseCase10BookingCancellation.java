package uc10;

import java.util.*;

/**
 * Use Case 10: Booking Cancellation & Inventory Rollback
 *
 * Demonstrates safe rollback using Stack (LIFO).
 *
 * @author Venky
 * @version 10.0
 */
public class UseCase10BookingCancellation {

    // Inventory
    private static Map<String, Integer> inventory = new HashMap<>();

    // Booking history (confirmed bookings)
    private static Map<String, Reservation> bookingHistory = new HashMap<>();

    // Stack for rollback (released room IDs)
    private static Stack<String> rollbackStack = new Stack<>();

    static {
        inventory.put("Single", 2);
        inventory.put("Double", 1);
        inventory.put("Suite", 1);
    }

    /**
     * Confirm booking (simulate UC6)
     */
    public static void confirmBooking(Reservation r) {

        int available = inventory.getOrDefault(r.getRoomType(), 0);

        if (r.getRoomsBooked() <= available) {

            inventory.put(r.getRoomType(), available - r.getRoomsBooked());
            bookingHistory.put(r.getReservationId(), r);

            System.out.println("Booking confirmed: " + r.getReservationId());

        } else {
            System.out.println("Booking failed: Not enough rooms");
        }
    }

    /**
     * Cancel booking and rollback
     */
    public static void cancelBooking(String reservationId) {

        System.out.println("\nCancelling booking: " + reservationId);

        // Validate existence
        if (!bookingHistory.containsKey(reservationId)) {
            System.out.println("Cancellation failed: Reservation not found");
            return;
        }

        Reservation r = bookingHistory.get(reservationId);

        // Restore inventory
        int current = inventory.get(r.getRoomType());
        inventory.put(r.getRoomType(), current + r.getRoomsBooked());

        // Push to rollback stack (simulate released IDs)
        rollbackStack.push(reservationId);

        // Remove from history
        bookingHistory.remove(reservationId);

        System.out.println("Cancellation successful: " + reservationId);
    }

    /**
     * Display inventory
     */
    public static void displayInventory() {

        System.out.println("\n--- Inventory ---");

        for (String type : inventory.keySet()) {
            System.out.println(type + ": " + inventory.get(type));
        }
    }

    /**
     * Display rollback stack
     */
    public static void displayRollbackStack() {

        System.out.println("\n--- Rollback Stack (LIFO) ---");
        System.out.println(rollbackStack);
    }

    /**
     * Main method
     */
    public static void main(String[] args) {

        // Create bookings
        Reservation r1 = new Reservation("RES101", "Single", 1);
        Reservation r2 = new Reservation("RES102", "Double", 1);

        confirmBooking(r1);
        confirmBooking(r2);

        displayInventory();

        // Cancel bookings
        cancelBooking("RES101");
        cancelBooking("RES999"); // invalid

        displayInventory();
        displayRollbackStack();
    }
}