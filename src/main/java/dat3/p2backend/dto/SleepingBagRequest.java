package dat3.p2backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SleepingBagRequest {

  Double height;
  Double environmentTemperatureMin;
  Double environmentTemperatureMax;

  Boolean isColdSensitive;

}
