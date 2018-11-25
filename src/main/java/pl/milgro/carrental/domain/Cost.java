package pl.milgro.carrental.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity(name = "pricelist")
public class Cost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "costname")
    private String name;

    @Column
    private Double price;

    public Cost(String name, Double price) {
        this.name = name;
        this.price = price;
    }
}
