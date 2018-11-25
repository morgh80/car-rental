package pl.milgro.carrental.model.car;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Car {
    private final String registrationNumber;
    private final String model;
    private final CarPriceCategory carPriceCategory;
    private final Transition transition;
    private final Fuel fuel;
    private final Integer fuelTankCapacity;
    private Integer mileage;
    private Integer isRented;

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

    public enum Transition {
        MANUAL, AUTOMATIC
    }

}
