package pl.bicyclerental.service;

import static pl.bicyclerental.model.Status.FREE;
import static pl.bicyclerental.model.Status.OCCUPIED;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bicyclerental.BicycleStationRepository;
import pl.bicyclerental.dto.BicycleRentalResponseDto;
import pl.bicyclerental.dto.BicycleStationDto;
import pl.bicyclerental.dto.BicycleStationUpdateDto;
import pl.bicyclerental.exception.BicycleStationNotFoundException;
import pl.bicyclerental.model.BicycleStation;
import pl.bicyclerental.model.Status;

@RequiredArgsConstructor
@Service
public class BicycleStationService {

  private final BicycleStationRepository bicycleStationRepository;
  private final ObjectMapper objectMapper;

  public BicycleStationDto find(Long id) {
    return bicycleStationRepository
        .findById(id)
        .map(
            bicycleStation ->
                BicycleStationDto.builder()
                    .name(bicycleStation.getName())
                    .bicycles(bicycleStation.getBicycles())
                    .bicycleStands(bicycleStation.getBicycleStands())
                    .build())
        .orElseThrow(() -> new BicycleStationNotFoundException("Bicycle station does not exist"));
  }

  public BicycleStationDto create(BicycleStationDto bicycleStationDto) {
    var bicycleStation = objectMapper.convertValue(bicycleStationDto, BicycleStation.class);
    bicycleStation = bicycleStationRepository.save(bicycleStation);
    return objectMapper.convertValue(bicycleStation, BicycleStationDto.class);
  }

  public List<BicycleRentalResponseDto> createSummary() {
    List<BicycleStation> stations = bicycleStationRepository.findAll();

    return stations.stream()
        .map(buildBicycleRentalResponseDtoFunction())
        .collect(Collectors.toList());
  }

  public BicycleStationDto update(Long id, BicycleStationUpdateDto updateDto) {
    var bicycleStation =
        bicycleStationRepository
            .findById(id)
            .orElseThrow(
                () -> new BicycleStationNotFoundException("Bicycle station does not exist"));

    bicycleStation.setName(updateDto.getName());
    bicycleStation = bicycleStationRepository.save(bicycleStation);

    return BicycleStationDto.builder()
        .name(bicycleStation.getName())
        .bicycleStands(bicycleStation.getBicycleStands())
        .bicycles(bicycleStation.getBicycles())
        .build();
  }

  public void delete(Long id) {
    bicycleStationRepository.deleteById(id);
  }

  private Function<BicycleStation, BicycleRentalResponseDto>
      buildBicycleRentalResponseDtoFunction() {
    return bicycleStation ->
        BicycleRentalResponseDto.builder()
            .name(bicycleStation.getName())
            .freeStands(extractCountOfStands(bicycleStation, FREE))
            .freeBicycles(extractCountOfBicycles(bicycleStation, FREE))
            .occupiedStands(extractCountOfStands(bicycleStation, OCCUPIED))
            .build();
  }

  private long extractCountOfStands(BicycleStation bicycleStation, Status status) {
    return bicycleStation.getBicycleStands().stream()
        .filter(bicycleStand -> bicycleStand.getStandStatus().equals(status))
        .count();
  }

  private long extractCountOfBicycles(BicycleStation bicycleStation, Status status) {
    return bicycleStation.getBicycles().stream()
        .filter(bicycle -> bicycle.getBicycleStatus().equals(status))
        .count();
  }
}
