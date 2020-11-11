package pl.bicyclerental.model;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class BicycleStation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @JoinColumn(name = "station_id")
  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private Set<Bicycle> bicycles;

  @JoinColumn(name = "station_id")
  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private Set<BicycleStand> bicycleStands;
}
