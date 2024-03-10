package midart.api.midart.repository;

import midart.api.midart.dto.response.FollowedResponse;
import midart.api.midart.dto.response.FollowerResponse;
import midart.api.midart.model.Follower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowersRepository extends JpaRepository<Follower, Long> {
    @Query("SELECT f FROM Follower f " +
            "WHERE f.follower_user.id = :authUserId " +
            "AND f.followed_user.id = :followerUserId")
    Follower findByFollowerUserIdAndFollowedUserId(
            @Param("authUserId") Long authUserId,
            @Param("followerUserId") Long followerUserId
    );

    @Query("SELECT new midart.api.midart.dto.response.FollowerResponse(f.follower_user.id, f.follower_user.firstname, f.follower_user.profile_image) FROM Follower f WHERE f.followed_user.id = :userId")
    List<FollowerResponse> findAllFollowersByUserId(@Param("userId") Long userId);


    @Query("SELECT new midart.api.midart.dto.response.FollowedResponse(f.followed_user.id, f.followed_user.firstname, f.followed_user.profile_image) FROM Follower f WHERE f.followed_user.id = :userId")
    List<FollowedResponse> findAllFollowedByUserId(@Param("userId") Long userId);
}
