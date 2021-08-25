package org.codejudge.sb.reposotories;

import org.codejudge.sb.models.CovidSymptoms;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CovidSymptomsRepository extends JpaRepository<CovidSymptoms, Long> {
    Optional<CovidSymptoms> findUserByUserId(String username);
}
