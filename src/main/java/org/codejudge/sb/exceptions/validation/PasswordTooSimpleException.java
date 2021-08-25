package org.codejudge.sb.exceptions.validation;

public class PasswordTooSimpleException extends ValidationException {
    public PasswordTooSimpleException(String message) {
        super(message);
    }
}
