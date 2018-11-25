package pl.milgro.carrental.model.rentalcosts;

import lombok.Getter;
import lombok.Setter;
import pl.milgro.carrental.domain.Cost;
import pl.milgro.carrental.model.rentalcosts.insurance.CarInsurence;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class RentalDetails {

    private Integer rentalPeriod;
    private CarInsurence insurance;
    private List<Cost> additionalCosts = new ArrayList<>();

    public RentalDetails(Integer rentalPeriod, CarInsurence carInsurence, Cost... Costs) {
        this.rentalPeriod = rentalPeriod;
        this.insurance = carInsurence;
        for (Cost cost : Costs) {
            this.additionalCosts.add(cost);
        }
    }

}





