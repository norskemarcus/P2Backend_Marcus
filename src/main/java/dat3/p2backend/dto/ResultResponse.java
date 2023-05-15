package dat3.p2backend.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultResponse {

  Double environmentTemperatureMin;
  Double minCost;
  Double maxCost;
  String innerMaterial;
  Boolean isInStore;

  public ResultResponse(Double environmentTemperatureMin, Double minCost, Double maxCost, String innerMaterial, Boolean isInStore) {
    this.environmentTemperatureMin = environmentTemperatureMin;
    this.minCost = minCost;
    this.maxCost = maxCost;
    this.innerMaterial = innerMaterial;
    this.isInStore = isInStore;
  }
}
