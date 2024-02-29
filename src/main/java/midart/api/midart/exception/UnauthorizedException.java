package midart.api.midart.exception;

import lombok.Getter;

@Getter
public class UnauthorizedException extends Throwable {
    public UnauthorizedException(String string) {
    }
}
