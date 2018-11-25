package pl.milgro.carrental.model.rentalcosts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.milgro.carrental.domain.Cost;
import pl.milgro.carrental.model.car.Car;
import pl.milgro.carrental.model.car.CarPriceCategory;
import pl.milgro.carrental.service.PriceListService;

@Getter
@Setter
@NoArgsConstructor
@Component
public class CostCalculator {
    private PriceListService priceListService;

    public Double calculateRentCost(Car car, RentalDetails rentalDetails) {
        Double dailyCarCost = getDailyCost(car);
        Double dailyInsuranceCost = getDailyInsuranceCost(rentalDetails, dailyCarCost);
        Double dailyAdditionalCosts = getAdditionalCosts(rentalDetails);
        Double fuelCost = getFuelCost(car);
        return (dailyInsuranceCost + dailyAdditionalCosts) * rentalDetails.getRentalPeriod() + fuelCost;
    }

    public Double getDailyCost(Car car) {
        switch (car.getCarPriceCategory()) {
            case MINI:
                return priceListService.getCostByName(CarPriceCategory.MINI.getDbName()).getPrice();
            case COMPACT:
                return priceListService.getCostByName(CarPriceCategory.COMPACT.getDbName()).getPrice();
            case ECONOMY:
                return priceListService.getCostByName(CarPriceCategory.ECONOMY.getDbName()).getPrice();
            case STANDARD:
                return priceListService.getCostByName(CarPriceCategory.STANDARD.getDbName()).getPrice();
            case PREMIUM:
                return priceListService.getCostByName(CarPriceCategory.PREMIUM.getDbName()).getPrice();
            case ELECTRIC:
                return priceListService.getCostByName(CarPriceCategory.ELECTRIC.getDbName()).getPrice();
            default:
                return null;
        }
    }

    public Double getDailyInsuranceCost(RentalDetails rentalDetails, Double dailyCarCost) {
        return priceListService.getCostByName(rentalDetails.getInsurance().name()).getPrice() * dailyCarCost;
    }

    public Double getFuelCost(Car car) {
        switch (car.getFuel()) {
            case PETROL:
                return car.getFuelTankCapacity() * priceListService.getCostByName(Car.Fuel.PETROL.getDbName()).getPrice();
            case DIESEL:
                return car.getFuelTankCapacity() * priceListService.getCostByName(Car.Fuel.DIESEL.getDbName()).getPrice();
            default:
                return 0.0;
        }
    }

    public Double getAdditionalCosts(RentalDetails rentalDetails) {
        Double costsSum = 0.0;
        for (Cost cost : rentalDetails.getAdditionalCosts()) {
            costsSum += cost.getPrice();
        }
        return costsSum;
    }

    @Autowired
    public CostCalculator(PriceListService priceListService) {
        this.priceListService = priceListService;
    }
}
