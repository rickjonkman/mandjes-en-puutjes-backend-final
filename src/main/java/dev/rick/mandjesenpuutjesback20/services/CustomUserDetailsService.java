package dev.rick.mandjesenpuutjesback20.services;

import dev.rick.mandjesenpuutjesback20.dto.user.UserAuthDTO;
import dev.rick.mandjesenpuutjesback20.dto.user.UserOutputDTO;
import dev.rick.mandjesenpuutjesback20.models.user.Authority;
import dev.rick.mandjesenpuutjesback20.models.user.User;
import dev.rick.mandjesenpuutjesback20.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserAuthDTO userAuthDTO = userService.getUserAuthById(username);
//
//        String password = userAuthDTO.getPassword();
//
//        Set<Authority> authorities = userAuthDTO.getAuthoritySet();
//        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        for (Authority authority : authorities) {
//            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
//        }
//
//        return new User(username, password, grantedAuthorities);
//    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> optionalUser = userRepository.findById(username);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException(username);
        } else {
            User foundUser = optionalUser.get();
            String password = foundUser.getPassword();
            Set<Authority> authorities = foundUser.getAuthorities();


            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (Authority authority : authorities) {
                grantedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
            }

            return new org.springframework.security.core.userdetails.User(username, password, grantedAuthorities);
        }

    }
}
