package pl.milgro.carrental.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.milgro.carrental.domain.Cost;
import pl.milgro.carrental.domain.Car;
import pl.milgro.carrental.domain.Insurance;
import pl.milgro.carrental.domain.RentalOrder;
import pl.milgro.carrental.service.CostService;

@Getter
@Setter
@NoArgsConstructor
@Component
public class CostCalculator {
    private CostService costService;

    public Double calculateRentCost(RentalOrder rentalOrder) {
        Double dailyCarCost = getDailyCost(rentalOrder.getCar());
        Double dailyInsuranceCost = getDailyInsuranceCost(rentalOrder, dailyCarCost);
        Double dailyAdditionalCosts = getAdditionalCosts(rentalOrder);
        Double fuelCost = getFuelCost(rentalOrder.getCar());
        return (dailyInsuranceCost + dailyAdditionalCosts) * rentalOrder.getRentalPeriod() + fuelCost;
    }

    public Double getDailyCost(Car car) {
        switch (car.getCarPriceCategory()) {
            case MINI:
                return costService.getCostByName(CarPriceCategory.MINI.getDbName()).getPrice();
            case COMPACT:
                return costService.getCostByName(CarPriceCategory.COMPACT.getDbName()).getPrice();
            case ECONOMY:
                return costService.getCostByName(CarPriceCategory.ECONOMY.getDbName()).getPrice();
            case STANDARD:
                return costService.getCostByName(CarPriceCategory.STANDARD.getDbName()).getPrice();
            case PREMIUM:
                return costService.getCostByName(CarPriceCategory.PREMIUM.getDbName()).getPrice();
            case ELECTRIC:
                return costService.getCostByName(CarPriceCategory.ELECTRIC.getDbName()).getPrice();
            default:
                return null;
        }
    }

    public Double getDailyInsuranceCost(RentalOrder rentalOrder, Double dailyCarCost) {
        double insuranceCost = 1;
        for (Insurance insurance : rentalOrder.getInsurance()) {
            insuranceCost *= insurance.getCostRate();
        }
        return insuranceCost * dailyCarCost;
    }

    public Double getFuelCost(Car car) {
        switch (car.getFuel()) {
            case PETROL:
                return car.getFuelTankCapacity() * costService.getCostByName(Car.Fuel.PETROL.getDbName()).getPrice();
            case DIESEL:
                return car.getFuelTankCapacity() * costService.getCostByName(Car.Fuel.DIESEL.getDbName()).getPrice();
            default:
                return 0.0;
        }
    }

    public Double getAdditionalCosts(RentalOrder rentalOrder) {
        Double costsSum = 0.0;
        for (Cost cost : rentalOrder.getAdditionalCosts()) {
            costsSum += cost.getPrice();
        }
        return costsSum;
    }

    @Autowired
    public CostCalculator(CostService costService) {
        this.costService = costService;
    }
}
