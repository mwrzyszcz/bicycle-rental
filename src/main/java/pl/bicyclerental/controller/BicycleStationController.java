package pl.bicyclerental.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bicyclerental.dto.BicycleRentalResponseDto;
import pl.bicyclerental.dto.BicycleStationDto;
import pl.bicyclerental.dto.BicycleStationUpdateDto;
import pl.bicyclerental.service.BicycleStationService;

@RequiredArgsConstructor
@RequestMapping(path = "/api/stations")
@RestController
public class BicycleStationController {

  private final BicycleStationService bicycleStationService;

  @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BicycleStationDto> findStation(@PathVariable Long id) {
    return ResponseEntity.ok(bicycleStationService.find(id));
  }

  @PostMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BicycleStationDto> create(
      @RequestBody BicycleStationDto bicycleStationDto) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(bicycleStationService.create(bicycleStationDto));
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    bicycleStationService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping(
      path = "/{id}",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BicycleStationDto> update(
      @PathVariable Long id, @RequestBody BicycleStationUpdateDto dto) {
    return ResponseEntity.ok(bicycleStationService.update(id, dto));
  }

  @GetMapping(path = "/summary", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<BicycleRentalResponseDto>> summary() {
    return ResponseEntity.ok(bicycleStationService.createSummary());
  }
}
