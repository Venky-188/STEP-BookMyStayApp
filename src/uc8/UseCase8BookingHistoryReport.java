package uc8;

import java.util.*;

/**
 * Use Case 8: Booking History & Reporting
 *
 * Stores confirmed bookings and generates reports.
 *
 * @author Venky
 * @version 8.0
 */
public class UseCase8BookingHistoryReport {

    // List to store booking history (ordered)
    private List<Reservation> bookingHistory = new ArrayList<>();

    /**
     * Add confirmed booking to history
     */
    public void addBooking(Reservation r) {
        bookingHistory.add(r);
    }

    /**
     * Display all bookings
     */
    public void displayBookings() {

        System.out.println("\n--- Booking History ---");

        for (Reservation r : bookingHistory) {
            System.out.println("Customer: " + r.getCustomerName());
            System.out.println("Room Type: " + r.getRoomType());
            System.out.println("Rooms: " + r.getRoomsRequested());
            System.out.println("----------------------");
        }
    }

    /**
     * Generate summary report
     */
    public void generateReport() {

        System.out.println("\n--- Booking Report ---");

        Map<String, Integer> report = new HashMap<>();

        for (Reservation r : bookingHistory) {
            String type = r.getRoomType();
            report.put(type, report.getOrDefault(type, 0) + r.getRoomsRequested());
        }

        for (String type : report.keySet()) {
            System.out.println(type + " Rooms Booked: " + report.get(type));
        }
    }

    /**
     * Main method
     */
    public static void main(String[] args) {

        UseCase8BookingHistoryReport system = new UseCase8BookingHistoryReport();

        // Simulate confirmed bookings (from UC6)
        system.addBooking(new Reservation("Venky", "Single", 2));
        system.addBooking(new Reservation("Rahul", "Suite", 1));
        system.addBooking(new Reservation("Anita", "Double", 2));

        // Display history
        system.displayBookings();

        // Generate report
        system.generateReport();
    }
}
