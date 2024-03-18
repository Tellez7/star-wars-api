package co.com.starwars.services.starwarsapi.util.exception;

import co.com.starwars.services.starwarsapi.model.payload.MessageResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomException {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<MessageResponse> handleEntityNotFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessageResponse.builder()
                .message(HttpStatus.NOT_FOUND.name())
                .build());
    }

    @ExceptionHandler(PersistenceException.class)
    public ResponseEntity<MessageResponse> handleEntityNoContentException() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(MessageResponse.builder()
                .message(HttpStatus.NO_CONTENT.name())
                .build());
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<MessageResponse> handleEntityNumberException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MessageResponse.builder()
                .message("Error en la solicitud")
                .build());
    }
}
