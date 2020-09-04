package yte.intern.spring.application.usercrud;

import lombok.Getter;
import lombok.Setter;
import yte.intern.spring.application.eventcrud.Event;
import java.util.Set;

@Getter
@Setter
public class UserDTO {

	private String username;

	private String mailAddress;

	private String password;

	private String name;

	private String surname;

	private String tcKimlikNo;

	private Set<Event> registeredEvents;

}
