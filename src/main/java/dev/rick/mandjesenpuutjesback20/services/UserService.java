package dev.rick.mandjesenpuutjesback20.services;

import dev.rick.mandjesenpuutjesback20.converters.UserConverter;
import dev.rick.mandjesenpuutjesback20.dto.user.*;
import dev.rick.mandjesenpuutjesback20.exceptions.NotAuthorized;
import dev.rick.mandjesenpuutjesback20.exceptions.RecordNotFound;
import dev.rick.mandjesenpuutjesback20.exceptions.UsernameTaken;
import dev.rick.mandjesenpuutjesback20.models.user.Authority;
import dev.rick.mandjesenpuutjesback20.models.user.User;
import dev.rick.mandjesenpuutjesback20.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter converter;
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, UserConverter converter, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.converter = converter;
        this.encoder = encoder;
    }

    public RegistrationDTO registerNewUser(UserInputDTO newUser) {

        User isUsernameTaken = findUserByUsername(newUser.getUsername());
        if (isUsernameTaken != null) {
            throw new UsernameTaken(newUser.getUsername());
        } else {
            User createdUser = converter.convertInputDTOToUser(newUser, encoder);
            userRepository.save(createdUser);
            return converter.convertToRegistrationDTO(createdUser);
        }
    }

    public boolean addAuthorityToUser(String username, String authority) {

        User foundUser = findUserByUsername(username);
        if (foundUser == null) {
            throw new RecordNotFound(username);
        } else {
            foundUser.addAuthority(new Authority(username, authority));
            userRepository.save(foundUser);
            return true;
        }

    }

    public UserOutputDTO getAuthenticatedUser(Principal principal) {
        User foundUser = findUserByUsername(principal.getName());

        if (foundUser.getUsername().equals(principal.getName())) {
            UserOutputDTO outputDTO;
            outputDTO = converter.convertUserToOutputDTO(foundUser);
            return outputDTO;
        } else {
            throw new NotAuthorized();
        }
    }

    public UserAuthDTO getUserAuthById(String username) {
        User foundUser = findUserByUsername(username);
        if (foundUser == null) {
            throw new RecordNotFound(username);
        } else {
            return converter.convertToAuthDTO(foundUser, encoder);
        }
    }

    public UserOutputDTO getUserById(String username) {
        User foundUser = findUserByUsername(username);

        if (foundUser.getUsername().equals(username) && foundUser.isEnabled()) {
            return converter.convertUserToOutputDTO(foundUser);
        } else {
            throw new RecordNotFound(username);
        }
    }

    public User findUserByUsername(String username) {
        Optional<User> optionalUser = userRepository.findById(username);
        return optionalUser.orElse(null);
    }
}
