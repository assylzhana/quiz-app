package assylzhan.project.quizapp.controllers;

import assylzhan.project.quizapp.dto.SignUpRequestDTO;
import assylzhan.project.quizapp.dto.SignInRequestDTO;
import assylzhan.project.quizapp.dto.AuthResponseDTO;
import assylzhan.project.quizapp.models.User;
import assylzhan.project.quizapp.services.UserService;
import assylzhan.project.quizapp.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/sign-up")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequestDTO signUpRequest) {
        User user = userService.addUser(new User(signUpRequest.getUsername(), signUpRequest.getEmail(), signUpRequest.getPassword()));
        String jwt = jwtService.generateToken(user);
        return ResponseEntity.ok(new AuthResponseDTO(jwt));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> authenticateUser(@RequestBody SignInRequestDTO signInRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword())
            );
            User user = (User) userService.loadUserByUsername(signInRequest.getUsername());
            String jwt = jwtService.generateToken(user);
            return ResponseEntity.ok(new AuthResponseDTO(jwt));
        } catch (AuthenticationException e) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }
}
