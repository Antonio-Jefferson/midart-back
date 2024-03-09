package midart.api.midart.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import midart.api.midart.dto.request.CommentRequest;
import midart.api.midart.dto.response.CommentsResponse;
import midart.api.midart.service.CommentsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/midart/api/v1")
@RequiredArgsConstructor
public class CommentsController {

    private final CommentsService commentsService;

    @PostMapping("/comments/{drawId}")
    public ResponseEntity<Void> postComment(@PathVariable Long drawId,@Valid @RequestBody CommentRequest comment){
        commentsService.postComment(drawId, comment);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<Void> putComment(@PathVariable Long commentId, @RequestBody CommentRequest comment){
        commentsService.putComment(commentId, comment);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId){
        commentsService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }

}
