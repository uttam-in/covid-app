package org.codejudge.sb.exceptions.validation;

public class UsernameTakenException extends ValidationException {
    public UsernameTakenException(String message) {
        super(message);
    }
}
