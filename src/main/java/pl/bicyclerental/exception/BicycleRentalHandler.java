package pl.bicyclerental.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class BicycleRentalHandler {

  @ExceptionHandler(BicycleStationNotFoundException.class)
  public ResponseEntity<ErrorMessage> handle(BicycleStationNotFoundException ex) {
    return ResponseEntity.status(NOT_FOUND)
        .body(new ErrorMessage(LocalDateTime.now(), NOT_FOUND.toString(), ex.getMessage()));
  }
}
