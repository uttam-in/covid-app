package org.codejudge.sb.reposotories;

import org.codejudge.sb.models.CovidUser;
import org.codejudge.sb.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CovidUserRepository extends JpaRepository<CovidUser, Long> {
    Optional<User> findUserByUsername(String username);
}
