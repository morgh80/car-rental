package pl.milgro.carrental.model.rentalcosts;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import pl.milgro.carrental.domain.Cost;
import pl.milgro.carrental.model.car.Car;
import pl.milgro.carrental.model.car.CarPriceCategory;
import pl.milgro.carrental.model.rentalcosts.insurance.CarInsurence;
import pl.milgro.carrental.service.PriceListService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CostCalculatorTest {

    @InjectMocks
    private CostCalculator costCalculator;

    @Mock
    private PriceListService priceListService;

    @Test
    public void isCalculatingRentCost() {
        //Given
        Car audi = new Car("Audi", CarPriceCategory.PREMIUM, "WH6767", Car.Transition.AUTOMATIC,
                Car.Fuel.PETROL, 50);
        RentalDetails rd = new RentalDetails(2, CarInsurence.BASIC_INSURANCE,
                new Cost("NAVIGATION_DAILY_COST", 10.0));

        Mockito.when(priceListService.getCostByName(CarPriceCategory.PREMIUM.getDbName()))
                .thenReturn(new Cost("REMIUM", 200.0));
        Mockito.when(priceListService.getCostByName(CarInsurence.BASIC_INSURANCE.name()))
                .thenReturn(new Cost("BASIC_INSURANCE", 1.2));
        Mockito.when(priceListService.getCostByName(Car.Fuel.PETROL.getDbName()))
                .thenReturn(new Cost("PETROL", 5.0));

        //When
        Double result = costCalculator.calculateRentCost(audi, rd);

        //Then
        Assert.assertEquals(750.0, result, 0.0);
    }

    @Test
    public void isCalculatingFuelCost() {
        //Given
        Car audi = new Car("Audi", CarPriceCategory.PREMIUM, "WH6767", Car.Transition.AUTOMATIC,
                Car.Fuel.PETROL, 50);
        Mockito.when(priceListService.getCostByName(Car.Fuel.PETROL.getDbName()))
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
        RentalDetails rd = new RentalDetails(1, CarInsurence.BASIC_INSURANCE);
        Mockito.when(priceListService.getCostByName(CarInsurence.BASIC_INSURANCE.name()))
                .thenReturn(new Cost("BASIC_INSURANCE", 1.2));
        //When
        Double result = costCalculator.getDailyInsuranceCost(rd, 100.0);
        //Then
        Assert.assertEquals(120.0, result, 0.0);
    }

    @Test
    public void isCalculatingCarDailyCost() {
        //Given
        Car audi = new Car("Audi", CarPriceCategory.PREMIUM, "WH6767", Car.Transition.AUTOMATIC,
                Car.Fuel.PETROL, 50);
        Mockito.when(priceListService.getCostByName(CarPriceCategory.PREMIUM.getDbName()))
                .thenReturn(new Cost("PREMIUM", 200.0));

        //When
        Double result = costCalculator.getDailyCost(audi);

        //Then
        Assert.assertEquals(200.0, result, 0.0);
    }

    @Test
    public void isCalculatingAdditionalCosts() {
        //Giben
        Car audi = new Car("Audi", CarPriceCategory.PREMIUM, "WH6767", Car.Transition.AUTOMATIC,
                Car.Fuel.PETROL, 50);
        RentalDetails rd = new RentalDetails(1, CarInsurence.BASIC_INSURANCE,
                new Cost("NAVIGATION_DAILY_COST", 10.0), new Cost("CHILD_SEAT_DAILY_COST", 15.0));

        //When
        Double result = costCalculator.getAdditionalCosts(rd);

        //Then
        Assert.assertEquals(25.0, result, 0.0);
    }

}
