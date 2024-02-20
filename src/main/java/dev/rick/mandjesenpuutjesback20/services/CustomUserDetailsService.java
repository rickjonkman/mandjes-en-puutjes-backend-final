package dev.rick.mandjesenpuutjesback20.services;

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User foundUser = new User();

        Optional<User> optionalUser = userRepository.findUserByUsername(username);
        if (optionalUser.isPresent()) {
            foundUser = optionalUser.get();
        }

        String password = null;
        Set<Authority> authorities = new HashSet<>();

        password = foundUser.getPassword();
        authorities = foundUser.getAuthorities();


        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        for (Authority authority : authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
        }

        return new org.springframework.security.core.userdetails.User(username, password, grantedAuthorities);
    }
}
