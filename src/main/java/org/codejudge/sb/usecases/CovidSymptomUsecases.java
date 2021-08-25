package org.codejudge.sb.usecases;

import org.codejudge.sb.dtos.CreateCovidSymptomsDTO;
import org.codejudge.sb.dtos.CreateCovidUserDTO;
import org.codejudge.sb.exceptions.validation.UsernameTakenException;
import org.codejudge.sb.models.CovidSymptoms;
import org.codejudge.sb.models.CovidUser;
import org.codejudge.sb.models.ShowSeat;
import org.codejudge.sb.models.Symptom;
import org.codejudge.sb.reposotories.CovidSymptomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CovidSymptomUsecases {
    // We're not going to change the password-encoder for different requests/usecases
    @Autowired
    CovidSymptomsRepository covidUserRepository;

    public ResponseEntity updateSymptom(CreateCovidSymptomsDTO details) {
        CovidSymptoms symptom = new CovidSymptoms(details.getUserId(),details.isTravelHistory(),details.isContactWithCovidPatient());

        covidUserRepository.save(symptom);
        return new ResponseEntity<CovidSymptoms>(symptom, HttpStatus.CREATED);
    }

}
