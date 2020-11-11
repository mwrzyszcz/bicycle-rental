package pl.bicyclerental.dto;

import lombok.*;
import pl.bicyclerental.model.Bicycle;
import pl.bicyclerental.model.BicycleStand;

import java.util.Set;

@Builder
@Getter
@Setter
public class BicycleStationDto {

  private String name;

  private Set<Bicycle> bicycles;

  private Set<BicycleStand> bicycleStands;
}
