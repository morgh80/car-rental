package pl.milgro.carrental.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.milgro.carrental.domain.Car;
import pl.milgro.carrental.model.CarPriceCategory;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarServiceTest {

    @Autowired
    CarService carService;

    @Test
    @Transactional
    public void isGettingAllCars() {
        //Given
        Car audi = new Car("Audi", CarPriceCategory.PREMIUM, "WH6767", Car.Transition.AUTOMATIC,
                Car.Fuel.PETROL, 50);
        Car ford = new Car("Ford", CarPriceCategory.COMPACT, "WZ78868", Car.Transition.MANUAL, Car.Fuel.DIESEL, 40);

        carService.saveCar(audi);
        carService.saveCar(ford);

        //When
        List<Car> result = carService.getAllCars();

        //Then
        Assert.assertEquals(2, result.size());
        Assert.assertEquals(audi.getModel(), result.get(0).getModel());

    }

    @Test
    public void isFindingCarModels() {

    }

    @Test
    public void isFindingCarsWithRegistrationNumber() {

    }

    @Test
    public void isFindingCarsbyTransition() {

    }

    public void isFindingCarByEngine() {

    }

}
