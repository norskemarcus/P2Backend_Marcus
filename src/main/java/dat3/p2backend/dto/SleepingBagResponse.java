package dat3.p2backend.dto;

import dat3.p2backend.entity.SleepingBag;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SleepingBagResponse {

  Integer sku;
  String brand;
  String model;


  public SleepingBagResponse(SleepingBag sleepingBag) {
    this.sku = sleepingBag.getSku();
    this.brand = sleepingBag.getBrand();
    this.model = sleepingBag.getModel();
  }
}
