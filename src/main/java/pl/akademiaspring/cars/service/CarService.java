package pl.akademiaspring.cars.service;

import org.springframework.stereotype.Service;
import pl.akademiaspring.cars.model.Car;

import java.util.ArrayList;
import java.util.List;

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
}
