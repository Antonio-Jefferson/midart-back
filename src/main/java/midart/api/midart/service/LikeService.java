package midart.api.midart.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import midart.api.midart.exception.NotFoundException;
import midart.api.midart.model.Drawing;
import midart.api.midart.model.Like;
import midart.api.midart.model.User;
import midart.api.midart.repository.DrawingRepository;
import midart.api.midart.repository.LikeRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LikeService {

    private final LikeRepository likeRepository;
    private final DrawingRepository drawingRepository;

    public void postLike(Long drawId){
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Drawing draw = drawingRepository.findById(drawId).orElseThrow(() -> new NotFoundException("Drawing not found"));

        Like like = Like.builder()
                .user(authUser)
                .drawing(draw)
                .build();

        likeRepository.save(like);
    }

    public void deletePost(Long drawId) {
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Drawing draw = drawingRepository.findById(drawId).orElseThrow(() -> new NotFoundException("Drawing not found"));

        Like postLike = likeRepository.findLikeByDrawingAndUser(draw, authUser);

        if (postLike != null) {
            likeRepository.delete(postLike);
        } else {
            throw new NotFoundException("Like not found for the given user and drawing");
        }
    }
}
