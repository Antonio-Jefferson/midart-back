package midart.api.midart.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import midart.api.midart.model.Drawing;
import midart.api.midart.model.User;
import midart.api.midart.repository.DrawingRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
@RequiredArgsConstructor
@Slf4j
public class DrawingService {

    private final DrawingRepository drawingRepository;
    private final S3Service s3;

    public void postDrawing(String description, MultipartFile file)  {

        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Drawing newDrawing = Drawing.builder()
                .id(authUser.getId())
                .description(description)
                .image_url(s3.uploadFileIntoS3(file))
                .build();
        drawingRepository.save(newDrawing);
    }
}
