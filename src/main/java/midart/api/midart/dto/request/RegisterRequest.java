package midart.api.midart.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterRequest {

    @NotNull(message = "firstname may not be null")
    private String firstname;

    @NotNull(message = "lastname may not be null")
    private String lastname;

    @NotNull(message = "email may not be null")
    private String email;

    @NotNull(message = "password may not be null")
    private String password;

}
