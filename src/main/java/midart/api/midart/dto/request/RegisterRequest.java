package midart.api.midart.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterRequest {

    @Valid
    @NotBlank(message = "firstname may not be null")
    @Size(min = 3)
    private String firstname;

    @Valid
    @NotBlank(message = "lastname may not be null")
    private String lastname;

    @Valid
    @NotBlank(message = "email may not be null")
    @NotNull
    private String email;

    @Valid
    @NotBlank(message = "password may not be null")
    @Size(min = 4)
    private String password;

}
