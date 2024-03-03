package midart.api.midart.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentsResponse {
    private Long id;
    private Long userId;
    private String firstname;
    private String profile_image;
    private String comment;


}
