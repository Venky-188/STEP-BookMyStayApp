package uc9;

/**
 * Custom Exception for invalid booking
 */
public class InvalidBookingException extends Exception {

    public InvalidBookingException(String message) {
        super(message);
    }
}
