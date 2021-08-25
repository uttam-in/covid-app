package org.codejudge.sb.services.passwordencoder;

public interface PasswordEncoder {
    String encode(String plainText);
}
