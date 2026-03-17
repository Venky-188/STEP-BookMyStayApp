package uc4;

import java.util.HashMap;

/**
 * Inventory holds room availability
 */
public class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();

        inventory.put("Single", 5);
        inventory.put("Double", 0);  // unavailable
        inventory.put("Suite", 2);
    }

    public int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }

    public HashMap<String, Integer> getAllInventory() {
        return inventory;
    }
}