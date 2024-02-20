package dev.rick.mandjesenpuutjesback20.controllers;

import dev.rick.mandjesenpuutjesback20.dto.authentication.AuthRequestDTO;
import dev.rick.mandjesenpuutjesback20.dto.authentication.AuthResponseDTO;
import dev.rick.mandjesenpuutjesback20.services.CustomUserDetailsService;
import dev.rick.mandjesenpuutjesback20.utils.JwtUtility;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtUtility jwtUtility;

    public AuthenticationController(AuthenticationManager authenticationManager, CustomUserDetailsService customUserDetailsService, JwtUtility jwtUtility) {
        this.authenticationManager = authenticationManager;
        this.customUserDetailsService = customUserDetailsService;
        this.jwtUtility = jwtUtility;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAccessToken(@RequestBody AuthRequestDTO authRequestDTO) {

        String username = authRequestDTO.getUsername();
        String password = authRequestDTO.getPassword();

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("Username or password incorrect.");
        }

        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        final String jwt = jwtUtility.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponseDTO(jwt));
    }
}
