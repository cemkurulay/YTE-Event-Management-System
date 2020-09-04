package yte.intern.spring.application.usercrud;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import yte.intern.spring.application.eventcrud.Event;
import yte.intern.spring.application.login.Authority;
import yte.intern.spring.application.login.AuthorityRepository;


import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class UserService {
	private final PasswordEncoder passwordEncoder;
	private final AuthorityRepository authorityRepository;
	private final UserRepository userRepository;

	public String addUser(UserDTO userDTO) {

		Set<Authority> authorities = new HashSet<>();

		authorities.add(new Authority(null, new HashSet<>(), "PARTICIPANT"));

		authorityRepository.saveAll(authorities);

		//since there is no registered event for user yet, events are created as an empty Set
		Set<Event> events = new HashSet<>();
		SystemUser users = new SystemUser(
				null, userDTO.getUsername(), userDTO.getMailAddress(),passwordEncoder.encode(userDTO.getPassword()),
				userDTO.getName(), userDTO.getSurname(), userDTO.getTcKimlikNo(),
				authorities, events, null/*because teacher authority and tought lesson is not given yet */ ,
				true,true,true,true);

		userRepository.save(users);

		return "Başarıyla kullanıcı eklendi!";
	}

	@Transactional
	public String makeUserTeacher(String username) {

		SystemUser systemUser = userRepository.findByUsername(username);

		Set<Authority> authorities = new HashSet<>();

		authorities.add(new Authority(null, new HashSet<>(), "PARTICIPANT"));
		authorities.add(new Authority(null, new HashSet<>(), "TEACHER"));

		authorityRepository.saveAll(authorities);

		systemUser.setAuthorities(authorities);
		userRepository.save(systemUser);

		return "User has been updated successfully!";

	}

}
