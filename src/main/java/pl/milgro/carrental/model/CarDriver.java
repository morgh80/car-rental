package pl.milgro.carrental.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
public class CarDriver {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String adress;
    private String email;
    private String drivingLicense;
    private boolean isRentingCar;
}
