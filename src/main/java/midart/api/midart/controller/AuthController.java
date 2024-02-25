package midart.api.midart.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import midart.api.midart.config.TokenService;
import midart.api.midart.dto.request.AuthenticationRequest;
import midart.api.midart.dto.request.RegisterRequest;
import midart.api.midart.dto.response.AuthenticationResponse;
import midart.api.midart.model.Enums.Role;
import midart.api.midart.model.User;
import midart.api.midart.repository.UserRepository;
import midart.api.midart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/midart/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterRequest request) {
        if(Objects.nonNull(this.userRepository.findByEmail(request.getEmail()))) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(request.getPassword());
        User newUser = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(encryptedPassword)
                .role(Role.USER)
                .build();

        this.userRepository.save(newUser);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> register( @RequestBody AuthenticationRequest request) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(AuthenticationResponse.builder().token(token).build());
    }
}
