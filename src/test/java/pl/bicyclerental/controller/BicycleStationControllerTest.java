package pl.bicyclerental.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.bicyclerental.dto.BicycleStationDto;
import pl.bicyclerental.exception.BicycleStationNotFoundException;
import pl.bicyclerental.model.Bicycle;
import pl.bicyclerental.model.BicycleStand;
import pl.bicyclerental.model.Status;
import pl.bicyclerental.service.BicycleStationService;

@SpringBootTest
@AutoConfigureMockMvc
class BicycleStationControllerTest {

  @Autowired MockMvc mockMvc;

  @Autowired ObjectMapper objectMapper;

  @MockBean BicycleStationService bicycleStationService;

  @Test
  public void shouldReturnCorrectStationIfExist() throws Exception {
    // given
    var id = 1L;
    var stationName = "Nazwa stacji";

    Set<BicycleStand> bicycleStands = createBicycleStands();
    Set<Bicycle> bicycles = createBicycles();

    BicycleStationDto stationDto =
        BicycleStationDto.builder()
            .bicycleStands(bicycleStands)
            .bicycles(bicycles)
            .name(stationName)
            .build();

    var expectedJsonResponse = objectMapper.writeValueAsString(stationDto);
    BDDMockito.when(bicycleStationService.find(Mockito.eq(id))).thenReturn(stationDto);

    // when
    mockMvc
        .perform(MockMvcRequestBuilders.get("/api/stations/{id}", id))

        // then
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.content().json(expectedJsonResponse));
  }

  @Test
  public void shouldReturnNotFoundStatusIfNotExist() throws Exception {
    // given
    var id = 1L;
    var exceptionMessage = "Bicycle station not found";

    BDDMockito.when(bicycleStationService.find(Mockito.eq(id)))
        .thenThrow(new BicycleStationNotFoundException(exceptionMessage));

    // when
    mockMvc
        .perform(MockMvcRequestBuilders.get("/api/stations/{id}", id))

        // then
        .andExpect(MockMvcResultMatchers.status().isNotFound())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
  }

  private Set<BicycleStand> createBicycleStands() {
    BicycleStand bicycleStand = new BicycleStand();
    bicycleStand.setName("Nazwa stanowiska");
    bicycleStand.setId(1L);
    bicycleStand.setStandStatus(Status.FREE);
    return Set.of(bicycleStand);
  }

  private Set<Bicycle> createBicycles() {
    Bicycle bicycle = new Bicycle();
    bicycle.setBicycleStatus(Status.FREE);
    bicycle.setModelName("Nazwa roweru");
    bicycle.setId(1L);
    return Set.of(bicycle);
  }
}
