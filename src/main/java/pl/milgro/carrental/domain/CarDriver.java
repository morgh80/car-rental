package pl.milgro.carrental.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "DRIVER")
public class CarDriver {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    private String address;

    private String email;

    @Column(name = "driving_license")
    private String drivingLicenseNumber;

    public CarDriver(String firstName, String lastName, LocalDate birthDate, String address, String email, String drivingLicenseNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.address = address;
        this.email = email;
        this.drivingLicenseNumber = drivingLicenseNumber;
    }
}
