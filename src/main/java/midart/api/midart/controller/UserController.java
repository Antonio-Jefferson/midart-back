package midart.api.midart.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import midart.api.midart.dto.response.FollowedResponse;
import midart.api.midart.dto.response.FollowerResponse;
import midart.api.midart.dto.response.SearchUsersByPartialNameResponse;
import midart.api.midart.dto.response.UserProfileResponse;
import midart.api.midart.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/midart/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/search")
    public ResponseEntity<List<SearchUsersByPartialNameResponse>> getUserByParameterName(@RequestParam("name") String name){
        return ResponseEntity.ok(userService.searchUsersByPartialName(name));
    }

    @PutMapping("/profile-image/update")
    public ResponseEntity<Void> updateProfileImage(
            @Valid
            @NotNull(message = "file not null")
            @RequestParam(value = "file") MultipartFile file,
            @RequestParam(value = "id") Long userId
    ){
        userService.updateProfileImage(file, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}/follower")
    public ResponseEntity<List<FollowerResponse>> findAllFollowersByUserId(@PathVariable Long userId){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAllFollowersByUserId(userId));
    }

    @GetMapping("/{userId}/followed")
    public ResponseEntity<List<FollowedResponse>> findAllFollowedByUserId(@PathVariable Long userId){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAllFollowedByUserId(userId));
    }

    @GetMapping("/{userId}/profile-information")
    public ResponseEntity<UserProfileResponse> findProfileInformation(@PathVariable Long userId){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findProfileInformation(userId));
    }
}

