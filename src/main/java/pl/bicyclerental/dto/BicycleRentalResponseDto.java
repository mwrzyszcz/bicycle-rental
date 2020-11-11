package pl.bicyclerental.dto;

import lombok.*;

@Builder
@Getter
@Setter
public class BicycleRentalResponseDto {

  private long freeStands;
  private long occupiedStands;
  private long freeBicycles;
}
