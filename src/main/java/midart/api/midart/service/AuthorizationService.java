package midart.api.midart.service;

import lombok.RequiredArgsConstructor;
import midart.api.midart.dto.request.RegisterRequest;
import midart.api.midart.exception.EmailAlreadyExistsException;
import midart.api.midart.exception.NotFoundException;
import midart.api.midart.model.Enums.Role;
import midart.api.midart.model.User;
import midart.api.midart.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthorizationService implements UserDetailsService {

    private final UserRepository userRepository;


    public void register(RegisterRequest request) {
        if (Objects.nonNull(this.userRepository.findByEmail(request.getEmail()))) {
          throw new EmailAlreadyExistsException("E-mail already exists: " + request.getEmail());
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(request.getPassword());
        User newUser = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .profile_image("https://midart-profile-images.s3.amazonaws.com/default-image.png")
                .email(request.getEmail())
                .password(encryptedPassword)
                .role(Role.USER)
                .build();

        this.userRepository.save(newUser);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }
}
