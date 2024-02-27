package midart.api.midart.exception;

public class EmailAlreadyExistsException extends RuntimeException {
   public EmailAlreadyExistsException(String message){
       super(message);
   }
}
