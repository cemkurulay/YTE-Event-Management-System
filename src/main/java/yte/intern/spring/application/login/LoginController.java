package yte.intern.spring.application.login;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import yte.intern.spring.application.login.dto.LoginRequest;
import yte.intern.spring.application.login.dto.LoginResponse;


import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class LoginController {

	private final LoginService loginService;

	@PostMapping("/login")
	@PreAuthorize("permitAll()")
	public LoginResponse login(@Valid @RequestBody final LoginRequest loginRequest) {
		return loginService.login(loginRequest);
	}
}
