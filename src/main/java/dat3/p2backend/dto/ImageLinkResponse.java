package dat3.p2backend.dto;

import dat3.p2backend.entity.ImageLink;
import dat3.p2backend.entity.SleepingBag;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ImageLinkResponse {

  private Integer skuId;
  private String imageLink;
  private SleepingBag sleepingBag;


  public ImageLinkResponse(ImageLink imageLink) {
    this.skuId = imageLink.getSkuId();
    this.imageLink = imageLink.getImageLink();
    this.sleepingBag = imageLink.getSleepingBag();
  }
}
