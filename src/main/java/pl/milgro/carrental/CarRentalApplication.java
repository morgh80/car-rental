package pl.milgro.carrental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import pl.milgro.carrental.model.rentalcosts.CostCalculator;

@SpringBootApplication
public class CarRentalApplication {

    public static void main(String[] args) {
        ApplicationContext ac = SpringApplication.run(CarRentalApplication.class, args);
        CostCalculator rentalApplication = ac.getBean(CostCalculator.class);
        System.out.println(rentalApplication.getClass());
    }
}
