package midart.api.midart.repository;

import midart.api.midart.model.Drawing;
import midart.api.midart.model.Like;
import midart.api.midart.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    @Query("SELECT l FROM Like l WHERE l.drawing = :drawing AND l.user = :user")
    Like findLikeByDrawingAndUser(@Param("drawing") Drawing drawing, @Param("user") User user);
}
