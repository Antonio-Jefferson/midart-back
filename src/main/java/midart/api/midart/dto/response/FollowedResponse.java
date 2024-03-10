package midart.api.midart.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FollowedResponse {
    private Long followed_id;
    private String name;
    private String image_url;
}
