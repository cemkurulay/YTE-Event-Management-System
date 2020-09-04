package yte.intern.spring.application.usercrud;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import yte.intern.spring.application.usercrud.UserDTO;
import yte.intern.spring.application.usercrud.UserService;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/addUser")
    @PreAuthorize("permitAll()")
    public String addUser(@RequestBody UserDTO userDTO) {
        System.out.println(userDTO);
        return userService.addUser(userDTO);
    }


}
