package org.codejudge.sb.exceptions.validation;

import org.codejudge.sb.exceptions.BookMyShowException;

public class ValidationException extends BookMyShowException {
    public ValidationException(String message) {
        super(message);
    }
}
