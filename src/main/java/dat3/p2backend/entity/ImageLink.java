package dat3.p2backend.entity;

import jakarta.persistence.*;
import lombok.*;

import static java.lang.Integer.parseInt;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "image")
public class ImageLink {

  @Id
  private Integer skuId;
  private String imageLink;

  @OneToOne
  @JoinColumn(name = "sku")
 private SleepingBag sleepingBag;


  public ImageLink(String skuId, String imageLink) {
    this.skuId = parseInt(skuId);
    this.imageLink = imageLink;
  }
}
