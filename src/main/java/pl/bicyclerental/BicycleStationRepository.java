package pl.bicyclerental;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bicyclerental.model.BicycleStation;

@Repository
public interface BicycleStationRepository extends JpaRepository<BicycleStation, Long> {}
