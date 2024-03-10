package midart.api.midart.controller;

import lombok.RequiredArgsConstructor;
import midart.api.midart.service.FollowersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/midart/api/v1")
@RequiredArgsConstructor
public class FollowerController {

    private final FollowersService followersService;

    @PostMapping("/follow/{userId}")
    public ResponseEntity<Void> followUser(@PathVariable Long userId){
        followersService.followUser(userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("unfollow/{id}")
    public ResponseEntity<Void> unfollow(@PathVariable Long id){

        followersService.unfollow(id);
        return ResponseEntity.noContent().build();
    }
}
