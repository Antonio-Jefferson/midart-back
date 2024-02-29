package midart.api.midart.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import midart.api.midart.service.DrawingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @DeleteMapping("/drawing/{drawId}")
    public ResponseEntity<Void> deleteDrawing(@PathVariable Long drawId){
        drawingService.deleteDrawing(drawId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
