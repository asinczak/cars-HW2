package pl.akademiaspring.cars.service;

import org.springframework.stereotype.Service;
import pl.akademiaspring.cars.model.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private List<Car> carsList;

    public CarService() {
        this.carsList = new ArrayList();
        carsList.add(new Car(1L, "Volvo", "V70", "GREY"));
        carsList.add(new Car(2L, "Audi", "A6", "BLACK"));
        carsList.add(new Car(3L, "Kia", "Stinger", "RED"));
    }

    public List<Car> getCarsList() {
        return carsList;
    }

    public Optional<Car> getCarById (long id){
        return carsList.stream().filter(car -> car.getId() == id).findFirst();
    }

    public Optional<Car> getCarByColour(String colour) {
        return carsList.stream().filter(car -> car.getColour().equals(colour)).findFirst();
    }

    public boolean addCar(Car car) {
        return carsList.add(car);
    }

    public boolean modCar(Car car) {
        Optional<Car> firstCar = carsList.stream().filter(carFromList -> carFromList.getId() == car.getId()).findFirst();
        if(firstCar.isPresent()) {
            carsList.remove(firstCar.get());
            carsList.add(car);
            return true;
        }
        return false;
    }

    public boolean modCarElement(long id, String colour) {
        Optional<Car> firstCar = carsList.stream().filter(carFromList -> carFromList.getId() == id).findFirst();
        if(firstCar.isPresent()){
            firstCar.get().setColour(colour);
            return true;
        }
        return false;
    }

    public boolean removeCar(long id) {
        Optional<Car> firstCar = carsList.stream().filter(carFromList -> carFromList.getId() == id).findFirst();
        if(firstCar.isPresent()){
            carsList.remove(firstCar.get());
            return true;
        }
        return false;
    }
}
