package midart.api.midart.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import midart.api.midart.dto.response.CommentsResponse;
import midart.api.midart.dto.response.DrawingResponse;
import midart.api.midart.service.DrawingService;
import midart.api.midart.service.FavoritesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/midart/api/v1/drawing")
@RequiredArgsConstructor
public class DrawingController {

    private final DrawingService drawingService;
    private final FavoritesService favoritesService;

    @PostMapping
    public ResponseEntity<Void> postDrawing(
         @Valid
         @RequestParam(value = "description") String description,
         @NotNull(message = "file not null")
         @RequestParam(value = "file") MultipartFile file
    ) {
        drawingService.postDrawing(description, file);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<DrawingResponse>> getAllDrawings(){
        List<DrawingResponse> draws = drawingService.getAllDrawings();
        return ResponseEntity.ok(draws);
    }

    @GetMapping(path = "/{drawId}")
    public ResponseEntity<DrawingResponse> findDrawById(@PathVariable Long drawId) {
        return ResponseEntity.ok().body(drawingService.findDrawById(drawId));
    }

    @GetMapping("/{drawId}/comments")
    public ResponseEntity<List<CommentsResponse>>  findDrawingPostComments(@PathVariable Long drawId){
        return ResponseEntity.status(HttpStatus.OK).body(drawingService.findAllComment(drawId));
    }

    @DeleteMapping(path = "/{drawId}")
    public ResponseEntity<Void> deleteDrawing(@PathVariable Long drawId){
        drawingService.deleteDrawing(drawId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("{drawId}/favorites")
    public ResponseEntity<Void> saveDrawingFavorites(@PathVariable Long drawId){
        favoritesService.saveDrawingFavorites(drawId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/favorites")
    public ResponseEntity<List<DrawingResponse>> findAllDrawingsFavorites(){
        return ResponseEntity.ok().body(favoritesService.findAllDrawingsFavorites());
    }

    @DeleteMapping("{drawId}/favorites")
    public ResponseEntity<Void> removeDrawingFavorites(@PathVariable Long drawId){
        favoritesService.removeDrawingFavorite(drawId);
        return ResponseEntity.noContent().build();
    }

}
