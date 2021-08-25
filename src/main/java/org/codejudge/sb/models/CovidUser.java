package org.codejudge.sb.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.codejudge.sb.exceptions.validation.InvalidUsernameException;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "covidusers")
public class CovidUser extends CovidAuditable {
    // authentication
    private String username;
    private String phoneNumber;
    private String pinCode;

    // authorization
    @ManyToMany
    private Set<Role> roles = new HashSet<>();

    public CovidUser(String username, String phoneNumber, String pinCode) {
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.pinCode = pinCode;
    }

    public void setUsername(String username) {
        // validate
        if (username.length() < 2) {
            throw new InvalidUsernameException("Must have atleast 3 letters");
        }
        this.username = username;
    }




    public void addRole(Role role) {
        this.roles.add(role);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", roles=" + roles +
                super.toString() +
                '}';
    }
}


