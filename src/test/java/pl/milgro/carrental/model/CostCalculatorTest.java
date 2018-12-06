package pl.milgro.carrental.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import pl.milgro.carrental.domain.Cost;
import pl.milgro.carrental.domain.CarDriver;
import pl.milgro.carrental.domain.Car;
import pl.milgro.carrental.domain.RentalOrder;
import pl.milgro.carrental.domain.Insurance;
import pl.milgro.carrental.service.CostService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CostCalculatorTest {

    @InjectMocks
    private CostCalculator costCalculator;

    @Mock
    private CostService costService;

    @Test
    public void isCalculatingRentCost() {
        //Given
        Car audi = new Car("Audi", CarPriceCategory.PREMIUM, "WH6767", Car.Transition.AUTOMATIC,
                Car.Fuel.PETROL, 50);
        CarDriver driver = new CarDriver("Jan", "Kowalski",
                LocalDate.of(1970, 01, 01),
                "Warszawa", "test@test.pl", "WZK9080");
        List<Insurance> insurance = Arrays.asList(new Insurance("Collision Damage Waiver", 1.2));
        Cost additionalCost = new Cost("NAVIGATION_DAILY_COST", 10.0);
        RentalOrder rd = new RentalOrder(driver, audi, 2, insurance, additionalCost);

        Mockito.when(costService.getCostByName(CarPriceCategory.PREMIUM.getDbName()))
                .thenReturn(new Cost("REMIUM", 200.0));
        Mockito.when(costService.getCostByName(Car.Fuel.PETROL.getDbName()))
                .thenReturn(new Cost("PETROL", 5.0));

        //When
        Double result = costCalculator.calculateRentCost(rd);

        //Then
        Assert.assertEquals(750.0, result, 0.0);
    }

    @Test
    public void isCalculatingFuelCost() {
        //Given
        Car audi = new Car("Audi", CarPriceCategory.PREMIUM, "WH6767", Car.Transition.AUTOMATIC,
                Car.Fuel.PETROL, 50);
        Mockito.when(costService.getCostByName(Car.Fuel.PETROL.getDbName()))
                .thenReturn(new Cost("PETROL", 5.0));

        //When
        Double result = costCalculator.getFuelCost(audi);

        //Then
        Assert.assertEquals(250.0, result, 0.0);
    }

    @Test
    public void isCalculatingInsuranceCost() {
        //Given
        Car audi = new Car("Audi", CarPriceCategory.PREMIUM, "WH6767", Car.Transition.AUTOMATIC,
                Car.Fuel.PETROL, 50);
        CarDriver driver = new CarDriver("Jan", "Kowalski",
                LocalDate.of(1970, 01, 01),
                "Warszawa", "test@test.pl", "WZK9080");
        List<Insurance> insurance = Arrays.asList(new Insurance("Collision Damage Waiver", 1.2), new Insurance("Personal Accident Insurance", 1.1));
        Cost additionalCost = new Cost("NAVIGATION_DAILY_COST", 10.0);
        RentalOrder rd = new RentalOrder(driver, audi, 2, insurance, additionalCost);

        //When
        Double result = costCalculator.getDailyInsuranceCost(rd, 100.0);

        //Then
        Assert.assertEquals(132.0, result, 0.0);
    }

    @Test
    public void isCalculatingCarDailyCost() {
        //Given
        Car audi = new Car("Audi", CarPriceCategory.PREMIUM, "WH6767", Car.Transition.AUTOMATIC,
                Car.Fuel.PETROL, 50);
        Mockito.when(costService.getCostByName(CarPriceCategory.PREMIUM.getDbName()))
                .thenReturn(new Cost("PREMIUM", 200.0));

        //When
        Double result = costCalculator.getDailyCost(audi);

        //Then
        Assert.assertEquals(200.0, result, 0.0);
    }

    @Test
    public void isCalculatingAdditionalCosts() {
        //Given
        Car audi = new Car("Audi", CarPriceCategory.PREMIUM, "WH6767", Car.Transition.AUTOMATIC,
                Car.Fuel.PETROL, 50);
        CarDriver driver = new CarDriver("Jan", "Kowalski",
                LocalDate.of(1970, 01, 01),
                "Warszawa", "test@test.pl", "WZK9080");
        List<Insurance> insurance = Arrays.asList(new Insurance("Collision Damage Waiver", 1.2));
        RentalOrder rd = new RentalOrder(driver, audi, 1, insurance,
                new Cost("NAVIGATION_DAILY_COST", 10.0), new Cost("CHILD_SEAT_DAILY_COST", 15.0));

        //When
        Double result = costCalculator.getAdditionalCosts(rd);

        //Then
        Assert.assertEquals(25.0, result, 0.0);
    }

}
