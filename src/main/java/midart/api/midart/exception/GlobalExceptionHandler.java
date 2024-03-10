package midart.api.midart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionDetails> handlerMethodArgumentNotValidException(
            MethodArgumentNotValidException exception){
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));
        return new ResponseEntity<>(
                ValidationExceptionDetails.builder()
                        .title("Bad request Exception, Invalid Fields")
                        .status(HttpStatus.CONFLICT.value())
                        .details("Check the field(s) error")
                        .developerMessage( exception.getClass().getName())
                        .timestamp(LocalDateTime.now())
                        .fields(fields)
                        .fieldsMessage(fieldMessage)
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<NotFoundExceptionDetails> notFoundException(NotFoundException ex) {
        NotFoundExceptionDetails details = NotFoundExceptionDetails.builder()
                .title("Not Found")
                .status(HttpStatus.NOT_FOUND.value())
                .details(ex.getMessage())
                .developerMessage(ex.getClass().getName())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<EmailAlreadyExistsExceptionDetails> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        EmailAlreadyExistsExceptionDetails details = EmailAlreadyExistsExceptionDetails.builder()
                .title("E-mail already exists")
                .status(HttpStatus.CONFLICT.value())
                .details(ex.getMessage())
                .developerMessage(ex.getClass().getName())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(details, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<UnauthorizedExceptionDetails> handleEmailAlreadyExistsException(UnauthorizedException ex) {
        UnauthorizedExceptionDetails details = UnauthorizedExceptionDetails.builder()
                .title("Unauthorized access")
                .status(HttpStatus.UNAUTHORIZED.value())
                .details(ex.getMessage())
                .developerMessage(ex.getClass().getName())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(details, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ConflictExceptionDetails> handleEmailAlreadyExistsException(ConflictException ex) {
        ConflictExceptionDetails details = ConflictExceptionDetails.builder()
                .title("Conflict error")
                .status(HttpStatus.CONFLICT.value())
                .details(ex.getMessage())
                .developerMessage(ex.getClass().getName())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(details, HttpStatus.CONFLICT);
    }

}
