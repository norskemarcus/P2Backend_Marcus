package dat3.p2backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class SleepingBagExternal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String brand;
    String model;
    String sku;
    String cost;
    String personHeight;
    String comfortTemp;
    String lowerLimitTemp;
    String recommendedTemp;
    String innerMaterial;
    String productWeight;
    String colour;
    String season;
    String stockLocation;

    public SleepingBagExternal(
            String brand,
            String model,
            String sku,
            String cost,
            String personHeight,
            String comfortTemp,
            String lowerLimitTemp,
            String recommendedTemp,
            String innerMaterial,
            String productWeight,
            String colour,
            String season,
            String stockLocation
    ) {
        this.brand = brand;
        this.model = model;
        this.sku = sku;
        this.cost = cost;
        this.personHeight = personHeight;
        this.comfortTemp = comfortTemp;
        this.lowerLimitTemp = lowerLimitTemp;
        this.recommendedTemp = recommendedTemp;
        this.innerMaterial = innerMaterial;
        this.productWeight = productWeight;
        this.colour = colour;
        this.season = season;
        this.stockLocation = stockLocation;
    }
}
