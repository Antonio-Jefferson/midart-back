package midart.api.midart.controller;

import lombok.RequiredArgsConstructor;
import midart.api.midart.dto.response.SearchUsersByPartialNameResponse;
import midart.api.midart.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/midart/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users/search")
    public ResponseEntity<List<SearchUsersByPartialNameResponse>> getUserByParameterName(@RequestParam("name") String name){
        return ResponseEntity.ok(userService.searchUsersByPartialName(name));
    }
}

