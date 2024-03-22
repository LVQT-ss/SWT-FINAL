package tech.rent.be.exception;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class APIHandleException {
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> duplicate(BadCredentialsException exception){
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<?> duplicate(DuplicateException exception){
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InValidToken.class)
    public ResponseEntity<?> duplicate(InValidToken exception){
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ExpiredToken.class)
    public ResponseEntity<?> duplicate(ExpiredToken exception){
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BadRequest.class)
    public ResponseEntity<?> duplicate(BadRequest exception){
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
