package pl.milgro.carrental.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity(name = "pricelist")
public class Cost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "cost_name")
    private String name;

    private Double price;

    public Cost(String name, Double price) {
        this.name = name;
        this.price = price;
    }
}
