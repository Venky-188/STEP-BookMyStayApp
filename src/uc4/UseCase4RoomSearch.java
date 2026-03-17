package uc4;

import java.util.HashMap;

/**
 * Use Case 4: Room Search & Availability Check
 */
public class UseCase4RoomSearch {

    public static void main(String[] args) {

        // Inventory (read-only usage)
        RoomInventory inventory = new RoomInventory();

        // Room details (domain model)
        Room single = new Room("Single", 2000);
        Room doubleRoom = new Room("Double", 3500);
        Room suite = new Room("Suite", 5000);

        System.out.println("Available Rooms:\n");

        // Get inventory map
        HashMap<String, Integer> data = inventory.getAllInventory();

        // Loop through inventory
        for (String type : data.keySet()) {

            int available = data.get(type);

            // ✅ Only show available rooms
            if (available > 0) {

                if (type.equals("Single")) {
                    System.out.println("Type: " + single.getType());
                    System.out.println("Price: RS:" + single.getPrice());
                }

                else if (type.equals("Double")) {
                    System.out.println("Type: " + doubleRoom.getType());
                    System.out.println("Price: RS:" + doubleRoom.getPrice());
                }

                else if (type.equals("Suite")) {
                    System.out.println("Type: " + suite.getType());
                    System.out.println("Price: RS:" + suite.getPrice());
                }

                System.out.println("Available: " + available);
                System.out.println("----------------------");
            }
        }
    }
}