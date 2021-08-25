package org.codejudge.sb.exceptions.booking;

public class SeatsNotAvailableException extends BookingException {
    public SeatsNotAvailableException(String message) {
        super(message);
    }
}
