package midart.api.midart.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DrawingResponse {
    private Long id;
    private Long userId;
    private String profile_image;
    private String firstname;
    private String description;
    private String image_url;
    private Long quantityLikes;
    private Long quantityComments;
}
