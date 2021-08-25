package org.codejudge.sb.usecases;

import org.codejudge.sb.dtos.CreateCovidUserDTO;
import org.codejudge.sb.exceptions.validation.UsernameTakenException;
import org.codejudge.sb.models.CovidUser;
import org.codejudge.sb.reposotories.CovidUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CovidUserUsecases {
    // We're not going to change the password-encoder for different requests/usecases
    @Autowired
    CovidUserRepository covidUserRepository;

    public ResponseEntity createUser(CreateCovidUserDTO details) {
        CovidUser user = new CovidUser(details.getUsername(),details.getPhoneNumber(),details.getPinCode());
        if (covidUserRepository.findUserByUsername(details.getUsername()).isPresent()) {
            throw new UsernameTakenException(details.getUsername() + " is not available");
        }
        covidUserRepository.save(user);
        return new ResponseEntity<CovidUser>(user, HttpStatus.CREATED);
    }

}
