package pl.bicyclerental.model;

import lombok.Getter;

import javax.persistence.*;
import lombok.Setter;

@Getter
@Setter
@Table(name = "bicycle")
@Entity
public class Bicycle {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String modelName;

  @Enumerated(value = EnumType.STRING)
  private Status bicycleStatus;
}
