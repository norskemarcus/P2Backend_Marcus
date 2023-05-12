package dat3.p2backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Result {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int id;
  Double environmentTemperatureMin;
  Double minCost;
  Double maxCost;
  String innerMaterial;
  Boolean isInStore;
  @OneToOne (mappedBy = "result")
  Member member;

  public Result(Double environmentTemperatureMin, Double minCost, Double maxCost, String innerMaterial, Boolean isInStore) {
    this.environmentTemperatureMin = environmentTemperatureMin;
    this.minCost = minCost;
    this.maxCost = maxCost;
    this.innerMaterial = innerMaterial;
    this.isInStore = isInStore;
  }
}
