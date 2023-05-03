package dat3.p2backend.dto;

import dat3.p2backend.entity.SleepingBag;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SleepingBagResponse {

  Integer id;
  String brand;
  String model;
  Double cost;


  public SleepingBagResponse(SleepingBag sleepingBag) {
    //this.id = sleepingBag.getId();
   /*
    this.brand = sleepingBag.getBrand();
    this.model = sleepingBag.getModel();
    this.cost = sleepingBag.getCost();
    */
  }
}
