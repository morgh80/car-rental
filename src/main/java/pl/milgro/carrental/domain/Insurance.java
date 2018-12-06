package pl.milgro.carrental.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity(name = "INSURANCE")
public class Insurance {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "insurance_type")
    private String name;

    @Column(name = "cost_rate")
    private Double costRate;

    public Insurance(String name, Double costRate) {
        this.name = name;
        this.costRate = costRate;
    }

}
