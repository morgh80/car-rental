package pl.milgro.carrental.model;

import lombok.Getter;
import lombok.Setter;
import pl.milgro.carrental.model.car.Car;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CarRental {

    private static CarRental instance;

    private List<Car> availableCars;
    private List<Car> rentedCars;

    public List<Car> getAvailableCarsByType(String model) {
       return availableCars.stream()
                .filter(car -> car.getModel().equals(model))
                .collect(Collectors.toList());
    }

    public void rentCar(Car car, CarDriver driver) {
//        car.getRentalDetails().setRented(true);
        driver.setRentingCar(true);
    }

    public void returnCar(Car car, Integer mileage, CarDriver driver) {
//        car.getRentalDetails().setRented(false);
        car.setMileage(mileage);
        driver.setRentingCar(false);
    }


    private CarRental() {
    }

    public static CarRental getInstance() {
        if (instance == null) {
            instance = new CarRental();
        }
        return instance;
    }

}
