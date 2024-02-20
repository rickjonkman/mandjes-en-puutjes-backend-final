package dev.rick.mandjesenpuutjesback20.controllers;

import dev.rick.mandjesenpuutjesback20.dto.user.RegistrationDTO;
import dev.rick.mandjesenpuutjesback20.dto.user.UserDetailsDTO;
import dev.rick.mandjesenpuutjesback20.dto.user.UserInputDTO;
import dev.rick.mandjesenpuutjesback20.dto.user.UserOutputDTO;
import dev.rick.mandjesenpuutjesback20.exceptions.FailedToAddAuthority;
import dev.rick.mandjesenpuutjesback20.services.UserService;
import dev.rick.mandjesenpuutjesback20.utils.InputValidator;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final InputValidator validator;

    public UserController(UserService userService, InputValidator validator) {
        this.userService = userService;
        this.validator = validator;
    }

//    OPEN TO ALL VISITORS
    @PostMapping("/register")
    public ResponseEntity<?> registerNewUser(@Valid @RequestBody UserInputDTO newUser, BindingResult bindingResult) {

        RegistrationDTO registrationDTO;

        if (bindingResult.hasFieldErrors()) {
            String responseBody = validator.inputValidator(bindingResult);
            return ResponseEntity.badRequest().body(responseBody);
        } else {
            registrationDTO = userService.registerNewUser(newUser);
        }

        long newUserID = registrationDTO.getUserId();
        String authority = "ROLE_USER";

        boolean isAuthorityAdded = userService.addAuthorityToUser(newUserID, authority);
        if (!isAuthorityAdded) {
            throw new FailedToAddAuthority(newUserID);
        }

        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/api/users/user/"+newUserID)
                .toUriString());
        return ResponseEntity.created(uri).body(registrationDTO);
    }


    //    PERMITTED FOR AUTHENTICATED USERS
    @GetMapping("/get-user")
    public ResponseEntity<UserOutputDTO> getAuthenticatedUser(Principal principal) {
        UserOutputDTO outputDTO = userService.getAuthenticatedUser(principal);
        return ResponseEntity.ok(outputDTO);
    }

    @GetMapping("/{userId}/get")
    public ResponseEntity<UserOutputDTO> getUserById(@PathVariable("userId") long userId) {
        UserOutputDTO outputDTO = userService.getUserById(userId);
        return ResponseEntity.ok(outputDTO);
    }

    @PutMapping("/{userId}/update")
    public ResponseEntity<UserOutputDTO> updateUserDetailsById(@PathVariable("userId") long userId, @RequestBody UserDetailsDTO userDetailsDTO) {
        UserOutputDTO outputDTO = userService.updateUserDetailsById(userId, userDetailsDTO);
        return ResponseEntity.ok(outputDTO);
    }
}
