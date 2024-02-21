package dev.rick.mandjesenpuutjesback20.converters;

import dev.rick.mandjesenpuutjesback20.dto.user.*;
import dev.rick.mandjesenpuutjesback20.models.user.Authority;
import dev.rick.mandjesenpuutjesback20.models.user.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

//    private final PasswordEncoder encoder;
//
//    public UserConverter(PasswordEncoder encoder) {
//        this.encoder = encoder;
//    }

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
