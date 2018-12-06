package pl.milgro.carrental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.milgro.carrental.domain.Car;
import pl.milgro.carrental.repository.CarRepository;

import java.util.List;

@Service
public class CarService {

    private CarRepository carRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public List<Car> getCarByModel(String model) {
        return carRepository.findByModel(model);
    }

    public List<Car> getCarWithRegistrationNumber(String registrationNumber) {
        return carRepository.findByRegistrationNumber(registrationNumber);
    }

    public List<Car> getCarsWithTransition(Car.Transition transition) {
        return carRepository.findByTransition(transition);
    }

    public List<Car> getCarsWithEngine(Car.Fuel fuel) {
        return carRepository.findByFuel(fuel);
    }

    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    public void deleleCarWithId(Long id) {
        carRepository.deleteById(id);
    }

    public void deleteCar(Car car) {
        carRepository.delete(car);
    }

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

}
