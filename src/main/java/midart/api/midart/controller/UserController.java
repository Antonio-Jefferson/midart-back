package midart.api.midart.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import midart.api.midart.dto.response.SearchUsersByPartialNameResponse;
import midart.api.midart.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/midart/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users/search")
    public ResponseEntity<List<SearchUsersByPartialNameResponse>> getUserByParameterName(@RequestParam("name") String name){
        return ResponseEntity.ok(userService.searchUsersByPartialName(name));
    }

    @PutMapping("/users/profile-image/update")
    public ResponseEntity<Void> updateProfileImage(
            @Valid
            @NotNull(message = "file not null")
            @RequestParam(value = "file") MultipartFile file,
            @RequestParam(value = "id") Long userId
    ){
        userService.updateProfileImage(file, userId);
        return ResponseEntity.ok().build();
    }
}

