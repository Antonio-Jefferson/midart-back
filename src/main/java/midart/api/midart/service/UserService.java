package midart.api.midart.service;

import lombok.RequiredArgsConstructor;
import midart.api.midart.config.TokenService;
import midart.api.midart.dto.request.AuthenticationRequest;
import midart.api.midart.dto.request.RegisterRequest;
import midart.api.midart.dto.response.AuthenticationResponse;
import midart.api.midart.dto.response.SearchUsersByPartialNameResponse;
import midart.api.midart.model.Enums.Role;
import midart.api.midart.model.User;
import midart.api.midart.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<SearchUsersByPartialNameResponse> searchUsersByPartialName(String name) {
        List<User> users = userRepository.findUsersByFirstnameStartingWith(name);

        return users.stream()
                .map(user -> SearchUsersByPartialNameResponse.builder()
                        .id(user.getId())
                        .name(user.getFirstname())
                        .build())
                .collect(Collectors.toList());
    }
}
