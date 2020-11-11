package pl.bicyclerental.dto;

import lombok.*;

@Builder
@Getter
@Setter
public class BicycleRentalResponseDto {

  private String name;
  private long freeStands;
  private long occupiedStands;
  private long freeBicycles;
}
