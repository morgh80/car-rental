package pl.milgro.carrental.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "RENTAL_ORDER")
public class RentalOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @OneToOne
    private CarDriver driver;

    @OneToOne
    private Car car;

    @Column(name = "rental_period")
    private Integer rentalPeriod;

    @ManyToMany
    @JoinTable(name="order_costs",
            joinColumns=@JoinColumn(name="rental_order_id"),
            inverseJoinColumns=@JoinColumn(name="insurance_id"))
    @Enumerated(EnumType.STRING)
    private List<Insurance> insurance;

    @ManyToMany
    @JoinTable(name="order_costs",
            joinColumns=@JoinColumn(name="rental_order_id"),
            inverseJoinColumns=@JoinColumn(name="cost_id"))
    private List<Cost> additionalCosts = new ArrayList<>();

    public RentalOrder(CarDriver driver, Car car, Integer rentalPeriod, List<Insurance> insurance, Cost... Costs) {
        this.driver = driver;
        this.car = car;
        this.rentalPeriod = rentalPeriod;
        this.insurance = insurance;
        for (Cost cost : Costs) {
            this.additionalCosts.add(cost);
        }
    }

}





