package midart.api.midart.repository;

import midart.api.midart.dto.response.CommentsResponse;
import midart.api.midart.model.Comment;
import midart.api.midart.model.Drawing;
import midart.api.midart.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT new midart.api.midart.dto.response.CommentsResponse(c.id, c.user.id, c.user.firstname, c.user.profile_image, c.comment) FROM Comment c WHERE c.drawing.id = :drawId")
    List<CommentsResponse> findAllByDrawingId(@Param("drawId") Long drawId);

    @Query("SELECT c FROM Comment c WHERE c.drawing = :drawing AND c.user = :user")
    Comment findCommentByDrawingAndUser(@Param("drawing") Drawing drawing, @Param("user") User user);
}
