package midart.api.midart.service;

import lombok.RequiredArgsConstructor;
import midart.api.midart.exception.ConflictException;
import midart.api.midart.exception.NotFoundException;
import midart.api.midart.model.Follower;
import midart.api.midart.model.User;
import midart.api.midart.repository.FollowersRepository;
import midart.api.midart.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FollowersService {

    private final UserRepository userRepository;
    private final FollowersRepository followersRepository;

    public void followUser(Long userId) {
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User followedUser = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));

        Follower follower = followersRepository.findByFollowerUserIdAndFollowedUserId(authUser.getId(),
                userId);

        if (follower != null) {
            throw new ConflictException("Authenticated user follows specific user.");
        }

        Follower newFollower = Follower.builder()
                .follower_user(authUser)
                .followed_user(followedUser)
                .build();

        followersRepository.save(newFollower);

    }

    public void unfollow(Long id) {
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Follower followExist = followersRepository.findById(id).orElseThrow(() -> new NotFoundException("Follow relationship not found"));

        if (!Objects.equals(followExist.getFollower_user().getId(), authUser.getId())) {
            throw new ConflictException("Unauthorized to unfollow this user");
        }

        followersRepository.delete(followExist);

    }
}
