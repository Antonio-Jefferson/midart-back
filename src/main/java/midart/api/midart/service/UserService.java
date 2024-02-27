package midart.api.midart.service;

import lombok.RequiredArgsConstructor;
import midart.api.midart.dto.response.SearchUsersByPartialNameResponse;
import midart.api.midart.model.User;
import midart.api.midart.repository.UserRepository;
import org.springframework.stereotype.Service;

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
