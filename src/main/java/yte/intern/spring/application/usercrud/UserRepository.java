package yte.intern.spring.application.usercrud;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SystemUser, Long> {
	SystemUser findByUsername(String username);
}
