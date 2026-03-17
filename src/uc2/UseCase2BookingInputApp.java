package uc2;

import java.util.Scanner;

/**
 * Use Case 2: User Input for Booking System
 *
 * This class demonstrates how to take user input from the console
 * and process basic booking details.
 *
 * @author Venky
 * @version 1.0
 */
public class UseCase2BookingInputApp {

    /**
     * Entry point of the application
     */
    public static void main(String[] args) {

        // Create Scanner object for input
        Scanner scanner = new Scanner(System.in);

        // Ask user name
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        // Ask number of rooms
        System.out.print("Enter number of rooms to book: ");
        int rooms = scanner.nextInt();

        // Display booking confirmation
        System.out.println("\n--- Booking Confirmation ---");
        System.out.println("Customer Name: " + name);
        System.out.println("Rooms Booked: " + rooms);
        System.out.println("Booking successful!");

        // Close scanner
        scanner.close();
    }
}