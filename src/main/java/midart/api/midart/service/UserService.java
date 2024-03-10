package midart.api.midart.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import midart.api.midart.dto.response.FollowedResponse;
import midart.api.midart.dto.response.FollowerResponse;
import midart.api.midart.dto.response.SearchUsersByPartialNameResponse;
import midart.api.midart.exception.NotFoundException;
import midart.api.midart.model.User;
import midart.api.midart.repository.FollowersRepository;
import midart.api.midart.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final FollowersRepository followersRepository;
    private final UserRepository userRepository;
    private final S3Service s3;

    public List<SearchUsersByPartialNameResponse> searchUsersByPartialName(String name) {
        List<User> users = userRepository.findUsersByFirstnameStartingWith(name);

        return users.stream()
                .map(user -> SearchUsersByPartialNameResponse.builder()
                        .id(user.getId())
                        .name(user.getFirstname())
                        .profile_image(user.getProfile_image())
                        .build())
                .collect(Collectors.toList());
    }

    public void updateProfileImage(MultipartFile file, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));

        try {
            String url = s3.uploadFileIntoS3(file);
            user.setProfile_image(url);
            userRepository.save(user);
        } catch (RuntimeException e) {
            log.error("Failed to process file: " + e.getMessage());
        }
    }

    public List<FollowerResponse> findAllFollowersByUserId(Long userId) {
        userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found with id: " + userId));

        List<FollowerResponse> followers = followersRepository.findAllFollowersByUserId(userId);
        return followers;
    }

    public List<FollowedResponse> findAllFollowedByUserId(Long userId) {
        userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found with id: " + userId));

        List<FollowedResponse> followeds = followersRepository.findAllFollowedByUserId(userId);
        return followeds;
    }
}
