package uc9;

import java.util.HashMap;

/**
 * Use Case 9: Error Handling & Validation
 *
 * Demonstrates validation, custom exceptions,
 * and fail-fast design.
 *
 * @author Venky
 * @version 9.0
 */
public class UseCase9ErrorHandlingValidation {

    // Inventory
    private static HashMap<String, Integer> inventory = new HashMap<>();

    static {
        inventory.put("Single", 3);
        inventory.put("Double", 2);
        inventory.put("Suite", 1);
    }

    /**
     * Validate booking request
     */
    public static void validateBooking(Reservation r) throws InvalidBookingException {

        // Validate room type
        if (!inventory.containsKey(r.getRoomType())) {
            throw new InvalidBookingException("Invalid room type: " + r.getRoomType());
        }

        // Validate rooms requested
        if (r.getRoomsRequested() <= 0) {
            throw new InvalidBookingException("Rooms requested must be greater than 0");
        }

        int available = inventory.get(r.getRoomType());

        // Prevent negative inventory
        if (r.getRoomsRequested() > available) {
            throw new InvalidBookingException(
                    "Not enough rooms available. Requested: "
                            + r.getRoomsRequested()
                            + ", Available: "
                            + available
            );
        }
    }

    /**
     * Process booking safely
     */
    public static void processBooking(Reservation r) {

        try {
            validateBooking(r); // Fail-fast validation

            // If valid → update inventory
            int current = inventory.get(r.getRoomType());
            inventory.put(r.getRoomType(), current - r.getRoomsRequested());

            System.out.println("Booking successful for " + r.getCustomerName());

        } catch (InvalidBookingException e) {
            // Graceful failure
            System.out.println("Booking failed: " + e.getMessage());
        }
    }

    /**
     * Main method
     */
    public static void main(String[] args) {

        // Test cases

        Reservation r1 = new Reservation("Venky", "Single", 2);   // valid
        Reservation r2 = new Reservation("Rahul", "Suite", 5);    // not enough rooms
        Reservation r3 = new Reservation("Anita", "Luxury", 1);   // invalid type
        Reservation r4 = new Reservation("Kiran", "Double", -1);  // invalid number

        processBooking(r1);
        processBooking(r2);
        processBooking(r3);
        processBooking(r4);
    }
}
