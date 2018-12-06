package pl.milgro.carrental.model;

import lombok.Getter;
import lombok.Setter;
import pl.milgro.carrental.domain.Car;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CarRental {

    private static CarRental instance;

    private CarRental() {
    }

    public static CarRental getInstance() {
        if (instance == null) {
            instance = new CarRental();
        }
        return instance;
    }

}
