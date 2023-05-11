package dat3.p2backend.dto;


import dat3.p2backend.entity.SleepingBag;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageLinkRequest {

  private Integer sku;
  private String imageLink;
  private SleepingBag sleepingBag;
}
