package yte.intern.spring.application.usercrud;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;
import yte.intern.spring.application.eventcrud.Event;
import yte.intern.spring.application.login.Authority;
import yte.intern.spring.application.usercrud.validators.TcKimlikNo;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class SystemUser implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String username;

    @Email
    @NotBlank
    private String mailAddress;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @TcKimlikNo
    @NotBlank
    private String tcKimlikNo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "USER_AUTHORITIES",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id")
    )
    private Set<Authority> authorities;

    @ManyToMany
    @JoinTable(
            name = "USER_EVENTS",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private Set<Event> events;

    @OneToOne(mappedBy = "teacher")
    private Event teachedEvent;

    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

}
