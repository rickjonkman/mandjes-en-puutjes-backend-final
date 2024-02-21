package dev.rick.mandjesenpuutjesback20.controllers;

import dev.rick.mandjesenpuutjesback20.dto.user.*;
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

        String username = registrationDTO.getUsername();
        String authority = "ROLE_USER";

        boolean isAuthorityAdded = userService.addAuthorityToUser(username, authority);
        if (!isAuthorityAdded) {
            throw new FailedToAddAuthority(username);
        }

        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/api/users/user/"+username)
                .toUriString());
        return ResponseEntity.created(uri).body(registrationDTO);
    }


    //    PERMITTED FOR AUTHENTICATED USERS
    @GetMapping("/user/get-user")
    public ResponseEntity<UserOutputDTO> getAuthenticatedUser(Principal principal) {
        UserOutputDTO outputDTO = userService.getAuthenticatedUser(principal);
        return ResponseEntity.ok(outputDTO);
    }

    @PutMapping("/user/change-preferences")
    public ResponseEntity<?> changePreferences(Principal principal, @RequestBody PreferencesDTO preferencesDTO) {
        userService.changePreferences(principal, preferencesDTO);
        return ResponseEntity.noContent().build();
    }

}
