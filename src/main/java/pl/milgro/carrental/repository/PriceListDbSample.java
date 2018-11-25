package pl.milgro.carrental.repository;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import pl.milgro.carrental.domain.Cost;
import pl.milgro.carrental.model.car.Car;
import pl.milgro.carrental.model.car.CarPriceCategory;
import pl.milgro.carrental.model.rentalcosts.insurance.CarInsurence;
import pl.milgro.carrental.model.rentalcosts.insurance.PersonalInsurance;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PriceListDbSample implements PriceListRepository {

    private List<Cost> priceList = new ArrayList<>();

    private final static Double COMPACT_CAR_RATE = 100.0;
    private final static Double ECONOMY_CAR_RATE = 90.0;
    private final static Double ELECTRIC_CAR_RATE = 150.0;
    private final static Double MINI_CAR_RATE = 80.0;
    private final static Double PREMIUM_CAR_RATE = 160.0;
    private final static Double STANDARD_CAR_RATE = 110.0;
    private final static Double NAVIGATION_DAILY_COST = 10.0;
    private final static Double CHILD_SEAT_DAILY_COST = 15.0;
    private final static Double FULL_INSURANCE = 1.1;
    private final static Double BASIC_INSURANCE = 1.2;
    private final static Double PERSONAL_ACCIDENT_INSURANCE = 1.05;
    private final static Double PETROL_PRICE = 5.0;
    private final static Double DIESEL_PRICE = 4.0;

    public PriceListDbSample() {
        priceList.add(new Cost(CarPriceCategory.COMPACT.getDbName(), COMPACT_CAR_RATE));
        priceList.add(new Cost(CarPriceCategory.ECONOMY.getDbName(), ECONOMY_CAR_RATE));
        priceList.add(new Cost(CarPriceCategory.ELECTRIC.getDbName(), ELECTRIC_CAR_RATE));
        priceList.add(new Cost(CarPriceCategory.MINI.getDbName(), MINI_CAR_RATE));
        priceList.add(new Cost(CarPriceCategory.PREMIUM.getDbName(), PREMIUM_CAR_RATE));
        priceList.add(new Cost(CarPriceCategory.STANDARD.getDbName(), STANDARD_CAR_RATE));

        priceList.add(new Cost(CarInsurence.BASIC_INSURANCE.name(), BASIC_INSURANCE));
        priceList.add(new Cost(CarInsurence.FULL_INSURANCE.name(), FULL_INSURANCE));
        priceList.add(new Cost(PersonalInsurance.PERSONAL_ACCIDENT_INSURANCE.name(), PERSONAL_ACCIDENT_INSURANCE));

        priceList.add(new Cost(Car.Fuel.PETROL.getDbName(), PETROL_PRICE));
        priceList.add(new Cost(Car.Fuel.DIESEL.getDbName(), DIESEL_PRICE));

        priceList.add(new Cost("NAVIGATION_DAILY_COST", NAVIGATION_DAILY_COST));
        priceList.add(new Cost("CHILD_SEAT_DAILY_COST", CHILD_SEAT_DAILY_COST));

    }

    @Override
    public List<Cost> findAll() {
        return priceList;
    }

    @Override
    public Cost save(Cost cost) {
        priceList.add(cost);
        return cost;
    }

    @Override
    public Optional<Cost> findById(Long id) {
        Optional<Cost> optionalCost = Optional.empty();
        for (Cost cost : priceList) {
            if (cost.getId().equals(id)) {
                optionalCost = Optional.ofNullable(cost);
            }
        }
        return optionalCost;
    }

    @Override
    public Optional<Cost> findByName(String name) {
        Optional<Cost> optionalCost = Optional.empty();
        for (Cost cost : priceList) {
            if (cost.getName().equals(name)) {
                optionalCost = Optional.ofNullable(cost);
            }
        }
        return optionalCost;
    }

    @Override
    public void deleteById(Long id) {
        List<Cost> tempList = new ArrayList<>();
        for (Cost cost : priceList) {
            if (!cost.getId().equals(id)) {
                tempList.add(cost);
            }
        }
        priceList = tempList;
    }

    @Override
    public void deleteByName(String name) {
        List<Cost> tempList = new ArrayList<>();
        for (Cost cost : priceList) {
            if (!cost.getName().equals(name)) {
                tempList.add(cost);
            }
        }
        priceList = tempList;
    }

    @Override
    public void deleteAll() {
        priceList = new ArrayList<>();
    }
}
