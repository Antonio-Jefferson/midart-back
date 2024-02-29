package midart.api.midart.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import midart.api.midart.service.DrawingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/midart/api/v1")
@RequiredArgsConstructor
public class DrawingController {

    private final DrawingService drawingService;

    @PostMapping("/drawing")
    public ResponseEntity<Void> postDrawing(
         @Valid
         @RequestParam(value = "description") String description,
         @NotNull(message = "file not null")
         @RequestParam(value = "file") MultipartFile file
    ) {
        drawingService.postDrawing(description, file);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
