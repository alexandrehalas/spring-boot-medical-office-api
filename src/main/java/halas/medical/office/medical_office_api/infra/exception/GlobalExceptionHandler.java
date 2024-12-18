package halas.medical.office.medical_office_api.infra.exception;

import halas.medical.office.medical_office_api.domain.BusinessException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> errorHandlerNotFound404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> errorHandlerBadRequest400(MethodArgumentNotValidException exception) {
        var fieldErrors = exception.getFieldErrors();

        return ResponseEntity.badRequest().body(fieldErrors.stream().map(ErrorValidationData::new));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> errorHandlerBadRequest400(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> errorHandlerBusinessException400(BusinessException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> errorHandlerBadCredentials401() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Bad Credentials");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> errorHandlerAuthentication401() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication Failed");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> errorHandlerAccessDenied403() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> errorHandlerInternalServerError500(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + ex.getLocalizedMessage());
    }

    private record ErrorValidationData(String field, String message) {
        public ErrorValidationData(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }

}
