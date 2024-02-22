package dev.rick.mandjesenpuutjesback20.services;

import dev.rick.mandjesenpuutjesback20.dto.user.UserInputDTO;
import dev.rick.mandjesenpuutjesback20.dto.user.UserOutputDTO;
import dev.rick.mandjesenpuutjesback20.exceptions.NameIsTakenException;
import dev.rick.mandjesenpuutjesback20.models.user.Authority;
import dev.rick.mandjesenpuutjesback20.models.user.User;
import dev.rick.mandjesenpuutjesback20.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    User userOne = new User();
    User userTwo = new User();

    Authority authorityOne = new Authority("fred@novi.nl", "USER");
    Authority authorityTwo = new Authority("marie@novi.nl", "USER");

    UserInputDTO userRegisterInput = new UserInputDTO();

    @BeforeEach
    void setUp() {

        userOne.setFirstname("Fred");
        userOne.setUsername("fred@novi.nl");
        userOne.setPassword("wachtwoord");
        userOne.setEnabled(true);
        userOne.setShowVegan(true);
        userOne.setShowVegetarian(true);
        userOne.setShowFish(true);
        userOne.setShowMeat(true);
        userOne.setAuthorities(Set.of(authorityOne));

        userRegisterInput.setFirstname("Piet");
        userRegisterInput.setUsername("piet@novi.nl");
        userRegisterInput.setPassword("wachtwoord");
        userRegisterInput.setEnabled(true);
        userRegisterInput.setShowVegan(true);
        userRegisterInput.setShowVegetarian(true);
        userRegisterInput.setShowFish(true);
        userRegisterInput.setShowMeat(true);

        userTwo.setFirstname("Marie");
        userTwo.setUsername("marie@novi.nl");
        userTwo.setPassword("wachtwoord");
        userTwo.setEnabled(true);
        userTwo.setShowVegan(true);
        userTwo.setShowVegetarian(true);
        userTwo.setShowFish(true);
        userTwo.setShowMeat(true);
        userTwo.setAuthorities(Set.of(authorityTwo));
        userRepository.save(userTwo);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void itShouldReturnRegisteredUser() {

        when(userRepository.findById(anyString())).thenReturn(Optional.of(userOne));

        UserOutputDTO outputDTO = userService.getUserById(userOne.getUsername());

        assertEquals("Fred", outputDTO.getFirstname());
        assertEquals("fred@novi.nl", outputDTO.getUsername());
        assertTrue(outputDTO.isEnabled());
        assertTrue(outputDTO.isShowMeat());
        assertTrue(outputDTO.isShowFish());
        assertTrue(outputDTO.isShowVegetarian());
        assertTrue(outputDTO.isShowVegan());
    }

    @Test
    void itShouldCheckIfUsernameIsTaken() {
        userRepository.save(userTwo);

        when(userRepository.save(userTwo)).thenReturn(userTwo);

//        assertTrue(userTwo);
    }
}