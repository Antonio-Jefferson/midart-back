package midart.api.midart.exception;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class UnauthorizedException  {
    public UnauthorizedException(String string) {
    }
}
