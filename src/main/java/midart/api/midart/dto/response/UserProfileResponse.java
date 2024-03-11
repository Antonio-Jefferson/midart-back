package midart.api.midart.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileResponse {
    private Long userId;
    private String name;
    private String profileImage;
    private Long quantityFollowers;
    private Long quantityFollowing;
    private Long quantityPostDraw;
    private List<DrawingResponse> drawings;
}
