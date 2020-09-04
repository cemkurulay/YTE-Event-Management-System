package yte.intern.spring.application.login;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.intern.spring.application.login.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
