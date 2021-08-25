package org.codejudge.sb.reposotories;

import org.codejudge.sb.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
