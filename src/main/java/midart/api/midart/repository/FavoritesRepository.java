package midart.api.midart.repository;

import midart.api.midart.model.Drawing;
import midart.api.midart.model.Favorites;
import midart.api.midart.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoritesRepository extends JpaRepository<Favorites, Long> {

    @Query("SELECT f FROM Favorites f WHERE f.user = :authUser ")
    List<Favorites> findAllByUser(@Param("authUser") User authUser);

    @Query("SELECT f FROM Favorites f WHERE f.user = :authUser AND f.drawing = :draw")
    Optional<Favorites> findByUserAndDrawing(@Param("authUser") User authUser, @Param("draw") Drawing draw);

}
