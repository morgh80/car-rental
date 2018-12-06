package pl.milgro.carrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.milgro.carrental.domain.Car;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {
    @Override
    List<Car> findAll();

    @Override
    Optional<Car> findById(Long id);

    List<Car> findByModel(String model);

    List<Car> findByRegistrationNumber(String registrationNumber);

    List<Car> findByTransition(Car.Transition transition);

    List<Car> findByFuel(Car.Fuel fuel);

    @Override
    <S extends Car> S save(S car);

    @Override
    void deleteById(Long id);

    @Override
    void delete(Car car);

}
