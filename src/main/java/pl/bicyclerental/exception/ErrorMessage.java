package pl.bicyclerental.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ErrorMessage {

  private final LocalDateTime date;
  private final String status;
  private final String message;
}
