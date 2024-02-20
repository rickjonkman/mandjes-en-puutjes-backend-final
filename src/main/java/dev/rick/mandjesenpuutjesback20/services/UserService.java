package dev.rick.mandjesenpuutjesback20.services;

import dev.rick.mandjesenpuutjesback20.converters.UserConverter;
import dev.rick.mandjesenpuutjesback20.dto.user.RegistrationDTO;
import dev.rick.mandjesenpuutjesback20.dto.user.UserDetailsDTO;
import dev.rick.mandjesenpuutjesback20.dto.user.UserInputDTO;
import dev.rick.mandjesenpuutjesback20.dto.user.UserOutputDTO;
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

    public UserService(UserRepository userRepository, UserConverter converter) {
        this.userRepository = userRepository;
        this.converter = converter;
    }

    public RegistrationDTO registerNewUser(UserInputDTO newUser) {

        User isUsernameTaken = findUserByUsername(newUser.getUsername());
        if (isUsernameTaken != null) {
            throw new UsernameTaken(newUser.getUsername());
        } else {
            User createdUser = converter.convertInputDTOToUser(newUser);
            userRepository.save(createdUser);
            return converter.convertToRegistrationDTO(createdUser);
        }
    }

    public boolean addAuthorityToUser(long userId, String authority) {

        User foundUser = findUserById(userId);
        if (foundUser == null) {
            throw new RecordNotFound(userId);
        } else {
            foundUser.addAuthority(new Authority(userId, authority));
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

    public UserOutputDTO getUserById(long userId) {
        User foundUser = findUserById(userId);

        if (foundUser == null) {
            throw new RecordNotFound(userId);
        } else {
            UserOutputDTO outputDTO;
            outputDTO = converter.convertUserToOutputDTO(foundUser);
            return outputDTO;
        }
    }

    public UserOutputDTO updateUserDetailsById(long userId, UserDetailsDTO userDetailsDTO) {
        User foundUser = findUserById(userId);

        if (foundUser == null) {
            throw new RecordNotFound(userId);
        } else {
            User updatedUser = converter.updateUserFromInputDTO(foundUser, userDetailsDTO);
            userRepository.save(updatedUser);

            UserOutputDTO outputDTO;
            outputDTO = converter.convertUserToOutputDTO(updatedUser);
            return outputDTO;
        }
    }



    public User findUserById(long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.orElse(null);
    }

    public User findUserByUsername(String username) {
        Optional<User> optionalUser = userRepository.findUserByUsername(username);
        return optionalUser.orElse(null);
    }
}
