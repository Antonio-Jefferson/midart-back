package midart.api.midart.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchUsersByPartialNameResponse {
    private Long id;
    private String name;
    private String profile_image;
}
