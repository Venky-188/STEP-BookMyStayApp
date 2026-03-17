package uc3;

import java.util.HashMap;

/**
 * Use Case 3: Centralized Room Inventory Management
 *
 * This class demonstrates how to manage room availability
 * using a centralized HashMap structure.
 *
 * @author Venky
 * @version 3.0
 */
public class UseCase3InventorySetup {

    // HashMap to store room type and availability
    private HashMap<String, Integer> inventory;

    /**
     * Constructor to initialize inventory
     */
    public UseCase3InventorySetup() {
        inventory = new HashMap<>();

        // Initialize room types
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
    }

    /**
     * Get available rooms for a type
     */
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    /**
     * Update room availability
     */
    public void updateAvailability(String roomType, int roomsBooked) {

        if (inventory.containsKey(roomType)) {
            int current = inventory.get(roomType);

            if (roomsBooked <= current) {
                inventory.put(roomType, current - roomsBooked);
                System.out.println("Booking successful for " + roomType);
            } else {
                System.out.println("Not enough " + roomType + " rooms available");
            }
        } else {
            System.out.println("Invalid room type");
        }
    }

    /**
     * Display full inventory
     */
    public void displayInventory() {
        System.out.println("\n--- Current Room Inventory ---");

        for (String type : inventory.keySet()) {
            System.out.println(type + " Rooms: " + inventory.get(type));
        }
    }

    /**
     * Main method (Entry point)
     */
    public static void main(String[] args) {

        UseCase3InventorySetup system = new UseCase3InventorySetup();

        // Show initial inventory
        system.displayInventory();

        // Perform booking
        system.updateAvailability("Single", 2);
        system.updateAvailability("Suite", 1);

        // Show updated inventory
        system.displayInventory();
    }
}