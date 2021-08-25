package org.codejudge.sb.exceptions.booking;


import org.codejudge.sb.exceptions.BookMyShowException;

public class BookingException extends BookMyShowException {
    public BookingException(String message) {
        super(message);
    }
}
