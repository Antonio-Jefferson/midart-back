package midart.api.midart.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import midart.api.midart.service.LikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/midart/api/v1")
@Slf4j
@RequiredArgsConstructor
public class LikeController {

    private  final LikeService likeService;

    @PostMapping("/like/{drawId}")
    public ResponseEntity<Void> postLiked(@PathVariable Long drawId){
        likeService.postLike(drawId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/like/{drawId}")
    public ResponseEntity<Void> deleteLiked(@PathVariable Long drawId){
        likeService.deletePost(drawId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
