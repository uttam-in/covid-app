package org.codejudge.sb.usecases;


import org.codejudge.sb.dtos.CreateUserDTO;
import org.codejudge.sb.models.User;
import org.codejudge.sb.reposotories.UserRepository;
import org.codejudge.sb.services.passwordencoder.PasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserUsecases {
    // We're not going to change the password-encoder for different requests/usecases
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserUsecases(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public ResponseEntity createUser(CreateUserDTO details) {
        User user = new User(details.getUsername());
        user.setPassword(details.getPassword(), passwordEncoder);
        userRepository.save(user);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    public ResponseEntity createCovidUser(CreateUserDTO details) {
        User user = new User(details.getUsername());
//        user.setPassword(details.getPassword(), passwordEncoder);
        userRepository.save(user);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    public boolean checkPasswordValid(User user, String password) {
        return user.checkPassword(password, passwordEncoder);
    }

    public void changePassword(User user, String newPassword) {
        // some other validations that might NOT belong in the User entity
        user.setPassword(newPassword, passwordEncoder);
    }
}
