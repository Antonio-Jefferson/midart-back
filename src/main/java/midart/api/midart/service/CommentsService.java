package midart.api.midart.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import midart.api.midart.dto.request.CommentRequest;
import midart.api.midart.exception.NotFoundException;
import midart.api.midart.model.Comment;
import midart.api.midart.model.Drawing;
import midart.api.midart.model.User;
import midart.api.midart.repository.CommentsRepository;
import midart.api.midart.repository.DrawingRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentsService {

    private final DrawingRepository drawingRepository;
    private final CommentsRepository commentsRepository;

    public void postComment(Long drawId, CommentRequest commentData) {
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Drawing draw = drawingRepository.findById(drawId).orElseThrow(() -> new NotFoundException("Drawing not found"));

        Comment newComment = Comment.builder()
                .user(authUser)
                .drawing(draw)
                .comment(commentData.getComment())
                .build();

        commentsRepository.save(newComment);
    }

    public void putComment(Long commentId, CommentRequest commentData) {
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Comment comment = commentsRepository.findById(commentId).orElseThrow(() -> new NotFoundException("Comment not found"));

        if (comment.getUser().getId().equals(authUser.getId())) {
            comment.setComment(commentData.getComment());
            commentsRepository.save(comment);
        } else {
            throw new NotFoundException("Comment does not belong to this user");
        }
    }

    public void deleteComment(Long commentId) {
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Comment comment = commentsRepository.findById(commentId).orElseThrow(() -> new NotFoundException("Comment not found"));

        if (comment.getUser().getId().equals(authUser.getId())) {
            commentsRepository.delete(comment);
        } else {
            throw new NotFoundException("Comment does not belong to this user");
        }
    }
}
