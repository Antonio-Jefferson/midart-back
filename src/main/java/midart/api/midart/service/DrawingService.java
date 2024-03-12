package midart.api.midart.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import midart.api.midart.dto.response.CommentsResponse;
import midart.api.midart.dto.response.DrawingResponse;
import midart.api.midart.exception.NotFoundException;
import midart.api.midart.model.Drawing;
import midart.api.midart.model.User;
import midart.api.midart.repository.CommentsRepository;
import midart.api.midart.repository.DrawingRepository;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class DrawingService {

    private final DrawingRepository drawingRepository;
    private final CommentsRepository commentsRepository;
    private final S3Service s3;

    public void postDrawing(String description, MultipartFile file)  {

        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Drawing newDrawing = Drawing.builder()
                .user(authUser)
                .description(description)
                .image_url(s3.uploadFileIntoS3(file))
                .build();
        drawingRepository.save(newDrawing);
    }

    @SneakyThrows
    public void deleteDrawing(Long drawId) {
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Drawing draw = drawingRepository.findById(drawId).orElseThrow(() -> new NotFoundException("Drawing not found"));

        if(!authUser.getId().equals(draw.getUser().getId())){
            throw new AuthenticationException("You are not authorized to delete this drawing");
        }
        drawingRepository.delete(draw);
    }

    public DrawingResponse findDrawById(Long id) {
        var drawExists = drawingRepository.findById(id).orElseThrow(null);

        return DrawingResponse.builder()
                .id(drawExists.getId())
                .userId(drawExists.getUser().getId()) // Assuming `getUser()` returns the associated user
                .profile_image(drawExists.getUser().getProfile_image()) // Assuming `getProfile_image()` returns the user's profile image URL
                .firstname(drawExists.getUser().getFirstname()) // Assuming `getFirstname()` returns the user's first name
                .description(drawExists.getDescription())
                .image_url(drawExists.getImage_url())
                .quantityComments((long) drawExists.getComments().size())
                .quantityLikes((long) drawExists.getLikes().size())
                .build();
    }

    public List<DrawingResponse> getAllDrawings() {
        List<Drawing> drawings = drawingRepository.findAll();
        List<DrawingResponse> drawingResponses = new ArrayList<>();
        for (Drawing drawing : drawings) {
            DrawingResponse drawingResponse = DrawingResponse.builder()
                    .id(drawing.getId())
                    .userId(drawing.getUser().getId())
                    .profile_image(drawing.getUser().getProfile_image())
                    .firstname(drawing.getUser().getFirstname())
                    .description(drawing.getDescription())
                    .image_url(drawing.getImage_url())
                    .quantityComments((long) drawing.getComments().size())
                    .quantityLikes((long) drawing.getLikes().size())
                    .build();
            drawingResponses.add(drawingResponse);
        }
        return drawingResponses;
    }

    public List<CommentsResponse> findAllComment(Long drawId) {
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Drawing draw = drawingRepository.findById(drawId).orElseThrow(() -> new NotFoundException("Drawing not found"));

        List<CommentsResponse> comments = commentsRepository.findAllByDrawingId(drawId);

        return comments;
    }
}
