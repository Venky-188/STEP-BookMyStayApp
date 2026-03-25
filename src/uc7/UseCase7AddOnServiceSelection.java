package uc7;

import java.util.*;

/**
 * Use Case 7: Add-On Service Selection
 *
 * Demonstrates how to attach optional services
 * to a reservation without modifying core booking logic.
 *
 * @author Venky
 * @version 7.0
 */
public class UseCase7AddOnServiceSelection {

    public static void main(String[] args) {

        // Map: Reservation ID → List of Services
        Map<String, List<Service>> serviceMap = new HashMap<>();

        // Create some services
        Service food = new Service("Food Package", 500);
        Service spa = new Service("Spa Access", 1000);
        Service pickup = new Service("Airport Pickup", 800);

        // Example reservation IDs (from UC6)
        String reservation1 = "RES101";
        String reservation2 = "RES102";

        // Add services to reservation 1
        List<Service> services1 = new ArrayList<>();
        services1.add(food);
        services1.add(spa);

        serviceMap.put(reservation1, services1);

        // Add services to reservation 2
        List<Service> services2 = new ArrayList<>();
        services2.add(pickup);

        serviceMap.put(reservation2, services2);

        // Display services + cost
        for (String resId : serviceMap.keySet()) {

            System.out.println("\nReservation ID: " + resId);

            List<Service> services = serviceMap.get(resId);

            int totalCost = 0;

            for (Service s : services) {
                System.out.println("Service: " + s.getName() + " | Price: ₹" + s.getPrice());
                totalCost += s.getPrice();
            }

            System.out.println("Total Add-On Cost: ₹" + totalCost);
        }
    }
}