package midart.api.midart.service;

import lombok.RequiredArgsConstructor;
import midart.api.midart.dto.response.DrawingResponse;
import midart.api.midart.exception.ConflictException;
import midart.api.midart.exception.NotFoundException;
import midart.api.midart.model.Drawing;
import midart.api.midart.model.Favorites;
import midart.api.midart.model.User;
import midart.api.midart.repository.DrawingRepository;
import midart.api.midart.repository.FavoritesRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavoritesService {
    private final DrawingRepository drawingRepository;
    private final FavoritesRepository favoritesRepository;

    public void saveDrawingFavorites(Long drawId) {
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Drawing draw = drawingRepository.findById(drawId)
                .orElseThrow(() -> new NotFoundException("Drawing not found"));
        Optional<Favorites> favorites = favoritesRepository.findByUserAndDrawing(authUser, draw);
        if (favorites.isPresent()) {
            throw new ConflictException("drawings are already in favorites.");
        }

        Favorites newFavorites = Favorites.builder()
                .user(authUser)
                .drawing(draw)
                .build();

        favoritesRepository.save(newFavorites);

    }

    public List<DrawingResponse> findAllDrawingsFavorites(){
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Favorites> favoritesList = favoritesRepository.findAllByUser(authUser);

        List<DrawingResponse> drawingResponses = new ArrayList<>();


        for (Favorites favorite : favoritesList) {
            DrawingResponse drawingResponse = DrawingResponse.builder()
                    .id(favorite.getId())
                    .userId(favorite.getUser().getId())
                    .profile_image(favorite.getUser().getProfile_image())
                    .firstname(favorite.getUser().getFirstname())
                    .description(favorite.getDrawing().getDescription())
                    .image_url(favorite.getDrawing().getImage_url())
                    .quantityComments((long) favorite.getDrawing().getComments().size())
                    .quantityLikes((long) favorite.getDrawing().getLikes().size())
                    .build();

            drawingResponses.add(drawingResponse);
        }

        return drawingResponses;
    }

    public void removeDrawingFavorite(Long drawId) {
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Drawing draw = drawingRepository.findById(drawId).orElseThrow(() -> new NotFoundException("Drawing not found"));

        Favorites favorite = favoritesRepository.findByUserAndDrawing(authUser, draw)
                .orElseThrow(() -> new NotFoundException("Favorite drawing not found"));

        favoritesRepository.delete(favorite);
    }
}
