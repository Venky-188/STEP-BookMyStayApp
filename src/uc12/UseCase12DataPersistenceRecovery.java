package uc12;

import java.io.*;
import java.util.*;

/**
 * Use Case 12: Data Persistence & Recovery
 *
 * Demonstrates saving and loading system state using serialization.
 *
 * @author Venky
 * @version 12.0
 */
public class UseCase12DataPersistenceRecovery {

    // File name
    private static final String FILE_NAME = "system_state.dat";

    /**
     * Data class to hold system state
     */
    static class SystemState implements Serializable {
        Map<String, Integer> inventory;
        List<String> bookings;

        public SystemState(Map<String, Integer> inventory, List<String> bookings) {
            this.inventory = inventory;
            this.bookings = bookings;
        }
    }

    /**
     * Save state to file
     */
    public static void saveState(SystemState state) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(state);
            System.out.println("System state saved successfully ✅");
        } catch (IOException e) {
            System.out.println("Error saving state: " + e.getMessage());
        }
    }

    /**
     * Load state from file
     */
    public static SystemState loadState() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            SystemState state = (SystemState) in.readObject();
            System.out.println("System state loaded successfully ✅");
            return state;
        } catch (FileNotFoundException e) {
            System.out.println("No saved state found. Starting fresh ⚠️");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading state: " + e.getMessage());
        }
        return null;
    }

    /**
     * Main method
     */
    public static void main(String[] args) {

        // Try loading previous state
        SystemState state = loadState();

        Map<String, Integer> inventory;
        List<String> bookings;

        if (state == null) {
            // Fresh start
            inventory = new HashMap<>();
            inventory.put("Single", 3);
            inventory.put("Double", 2);

            bookings = new ArrayList<>();
        } else {
            // Restore state
            inventory = state.inventory;
            bookings = state.bookings;
        }

        // Simulate new booking
        System.out.println("\nAdding new booking...");

        if (inventory.get("Single") > 0) {
            inventory.put("Single", inventory.get("Single") - 1);
            bookings.add("RES" + (bookings.size() + 1));
        }

        // Display current state
        System.out.println("\n--- Current Inventory ---");
        System.out.println(inventory);

        System.out.println("\n--- Booking History ---");
        System.out.println(bookings);

        // Save state before exit
        saveState(new SystemState(inventory, bookings));
    }
}
