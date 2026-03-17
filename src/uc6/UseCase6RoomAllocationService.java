package uc6;

import java.util.*;

/**
 * Use Case 6: Room Allocation Service
 *
 * This class processes booking requests,
 * assigns unique room IDs, and updates inventory safely.
 *
 * @author Venky
 * @version 6.0
 */
public class UseCase6RoomAllocationService {

    // Inventory (room type → available count)
    private HashMap<String, Integer> inventory = new HashMap<>();

    // Track allocated room IDs (prevents duplicates)
    private Set<String> allocatedRooms = new HashSet<>();

    // Map room type → assigned room IDs
    private HashMap<String, Set<String>> allocationMap = new HashMap<>();

    // Booking queue (FIFO)
    private Queue<Reservation> bookingQueue = new LinkedList<>();

    /**
     * Constructor - initialize inventory
     */
    public UseCase6RoomAllocationService() {
        inventory.put("Single", 3);
        inventory.put("Double", 2);
        inventory.put("Suite", 1);
    }

    /**
     * Add booking requests
     */
    public void addRequest(Reservation r) {
        bookingQueue.add(r);
    }

    /**
     * Process all booking requests
     */
    public void processBookings() {

        while (!bookingQueue.isEmpty()) {

            Reservation r = bookingQueue.poll(); // FIFO
            String type = r.getRoomType();
            int requested = r.getRoomsRequested();

            System.out.println("\nProcessing request for " + r.getCustomerName());

            int available = inventory.getOrDefault(type, 0);

            if (requested <= available) {

                for (int i = 0; i < requested; i++) {

                    // Generate unique room ID
                    String roomId = generateRoomId(type);

                    // Store in set (prevents duplicates)
                    allocatedRooms.add(roomId);

                    // Map room type → IDs
                    allocationMap
                            .computeIfAbsent(type, k -> new HashSet<>())
                            .add(roomId);

                    System.out.println("Allocated Room ID: " + roomId);
                }

                // Update inventory
                inventory.put(type, available - requested);

                System.out.println("Booking confirmed for " + r.getCustomerName());

            } else {
                System.out.println("Not enough rooms for " + r.getCustomerName());
            }
        }
    }

    /**
     * Generate unique room ID
     */
    private String generateRoomId(String type) {
        String id;

        do {
            id = type.substring(0, 2).toUpperCase() + new Random().nextInt(1000);
        } while (allocatedRooms.contains(id)); // ensure uniqueness

        return id;
    }

    /**
     * Display final allocation
     */
    public void displayAllocations() {

        System.out.println("\n--- Final Allocations ---");

        for (String type : allocationMap.keySet()) {
            System.out.println(type + " Rooms: " + allocationMap.get(type));
        }
    }

    /**
     * Main method
     */
    public static void main(String[] args) {

        UseCase6RoomAllocationService system = new UseCase6RoomAllocationService();

        // Add booking requests (from UC5)
        system.addRequest(new Reservation("Venky", "Single", 2));
        system.addRequest(new Reservation("Rahul", "Suite", 1));
        system.addRequest(new Reservation("Anita", "Double", 2));

        // Process bookings
        system.processBookings();

        // Show allocations
        system.displayAllocations();
    }
}