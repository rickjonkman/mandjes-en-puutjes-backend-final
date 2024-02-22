package dev.rick.mandjesenpuutjesback20.services;

import dev.rick.mandjesenpuutjesback20.dto.user.*;
import dev.rick.mandjesenpuutjesback20.exceptions.NameIsTakenException;
import dev.rick.mandjesenpuutjesback20.exceptions.NotAuthorized;
import dev.rick.mandjesenpuutjesback20.exceptions.RecordNotFound;
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
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public RegistrationDTO registerNewUser(UserInputDTO newUser) {

        User isUsernameTaken = findUserByUsername(newUser.getUsername());
        if (isUsernameTaken != null) {
            throw new NameIsTakenException(newUser.getUsername());
        } else {
            User createdUser = convertInputDTOToUser(newUser, encoder);
            userRepository.save(createdUser);
            return convertToRegistrationDTO(createdUser);
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
            outputDTO = convertUserToOutputDTO(foundUser);
            return outputDTO;
        } else {
            throw new NotAuthorized();
        }
    }

    public void changePreferences(Principal principal, PreferencesDTO preferencesDTO) {
        User foundUser = findUserByUsername(principal.getName());
        foundUser.setShowVegan(preferencesDTO.isShowVegan());
        foundUser.setShowVegetarian(preferencesDTO.isShowVegetarian());
        foundUser.setShowFish(preferencesDTO.isShowFish());
        foundUser.setShowMeat(preferencesDTO.isShowMeat());
        userRepository.save(foundUser);
    }

    public UserAuthDTO getUserAuthById(String username) {
        User foundUser = findUserByUsername(username);
        if (foundUser == null) {
            throw new RecordNotFound(username);
        } else {
            return convertToAuthDTO(foundUser, encoder);
        }
    }

    public UserOutputDTO getUserById(String username) {
        User foundUser = findUserByUsername(username);

        if (foundUser.getUsername().equals(username) && foundUser.isEnabled()) {
            return convertUserToOutputDTO(foundUser);
        } else {
            throw new RecordNotFound(username);
        }
    }

    public User findUserByUsername(String username) {
        Optional<User> optionalUser = userRepository.findById(username);
        return optionalUser.orElse(null);
    }

    public RegistrationDTO convertToRegistrationDTO(User createdUser) {
        RegistrationDTO dto = new RegistrationDTO();
        dto.setUsername(createdUser.getUsername());
        dto.setEnabled(createdUser.isEnabled());
        dto.setFirstname(createdUser.getFirstname());
        dto.setShowMeat(createdUser.isShowMeat());
        dto.setShowFish(createdUser.isShowFish());
        dto.setShowVegetarian(createdUser.isShowVegetarian());
        dto.setShowVegan(createdUser.isShowVegan());
        return dto;
    }

    public User convertInputDTOToUser(UserInputDTO newUser, PasswordEncoder encoder) {
        User createdUser = new User();
        createdUser.setFirstname(newUser.getUsername());
        createdUser.setUsername(newUser.getUsername());
        createdUser.setPassword(encoder.encode(newUser.getPassword()));
        createdUser.setEnabled(newUser.isEnabled());
        createdUser.setShowMeat(newUser.isShowMeat());
        createdUser.setShowFish(newUser.isShowFish());
        createdUser.setShowVegetarian(newUser.isShowVegetarian());
        createdUser.setShowVegan(newUser.isShowVegan());
        return createdUser;
    }

    public UserOutputDTO convertUserToOutputDTO(User user) {
        UserOutputDTO dto = new UserOutputDTO();
        dto.setFirstname(user.getFirstname());
        dto.setEnabled(user.isEnabled());
        dto.setUsername(user.getUsername());
        dto.setShowMeat(user.isShowMeat());
        dto.setShowFish(user.isShowFish());
        dto.setShowVegetarian(user.isShowVegetarian());
        dto.setShowVegan(user.isShowVegan());
        return dto;
    }

    public UserAuthDTO convertToAuthDTO(User user, PasswordEncoder encoder) {
        UserAuthDTO userAuthDTO = new UserAuthDTO();

        userAuthDTO.setUsername(user.getUsername());
        userAuthDTO.setPassword(encoder.encode(user.getPassword()));
        userAuthDTO.setAuthoritySet(user.getAuthorities());

        return userAuthDTO;
    }
}
