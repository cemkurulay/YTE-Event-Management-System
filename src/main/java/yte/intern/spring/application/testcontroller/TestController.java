package yte.intern.spring.application.testcontroller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@GetMapping("/fatih")
	public String name() {
		return "Muhammed Fatih Doğmuş";
	}

	@GetMapping("/user")
	@PreAuthorize("hasAnyAuthority('ADMIN', 'PARTICIPANT')")
	public String user() {
		return "Ben user'ım!";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String admin() {
		return "Ben adminim!";
	}

	@GetMapping("/teacher")
	@PreAuthorize("hasAuthority('TEACHER')")
	public String teacher() {
		return "Ben eğitmenim!";
	}
}
