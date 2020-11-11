package pl.bicyclerental.exception;

public class BicycleStationNotFoundException extends RuntimeException {
  public BicycleStationNotFoundException(String message) {
    super(message);
  }
}
