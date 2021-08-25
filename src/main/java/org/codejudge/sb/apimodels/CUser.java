package org.codejudge.sb.apimodels;


public class CUser {
    private String username;
    private String password;

    public CUser() {
    }

    public CUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
