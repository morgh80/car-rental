package pl.milgro.carrental.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.milgro.carrental.model.CarPriceCategory;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String model;

    @Column(name = "registration_number")
    private String registrationNumber;

    @Column(name = "price_category")
    @Enumerated(EnumType.STRING)
    private CarPriceCategory carPriceCategory;

    @Enumerated(EnumType.STRING)
    private Transition transition;

    @Enumerated(EnumType.STRING)
    private Fuel fuel;

    @Column(name = "tank_capacity")
    private Integer fuelTankCapacity;

    private Integer mileage;

    public Car(String model, CarPriceCategory carPriceCategory, String registrationNumber, Transition transition, Fuel fuel, Integer fuelTankCapacity) {
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.transition = transition;
        this.fuel = fuel;
        this.fuelTankCapacity = fuelTankCapacity;
        this.carPriceCategory = carPriceCategory;
    }

    @Getter
    public enum Fuel {
        PETROL("PETROL_PRICE"), DIESEL("DIESEL_PRICE");
        private String dbName;

        Fuel(String dbName) {
            this.dbName = dbName;
        }
    }
    @Getter
    public enum Transition {
        MANUAL, AUTOMATIC
    }

}
