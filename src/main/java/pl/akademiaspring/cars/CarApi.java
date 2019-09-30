package pl.akademiaspring.cars;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarApi {

    private List<Car> carsList;

    public CarApi() {
        this.carsList = new ArrayList();
        carsList.add(new Car(1L, "Volvo", "V70", "GREY"));
        carsList.add(new Car(2L, "Audi", "A6", "BLACK"));
        carsList.add(new Car(3L, "Kia", "Stinger", "RED"));
    }

    @GetMapping(produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Car>> getCars(){
        return new ResponseEntity<>(carsList, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Car> getCarById(@PathVariable long id){
        Optional<Car> firstCar = carsList.stream().filter(car -> car.getId() == id).findFirst();
        if(firstCar.isPresent()){
            return new ResponseEntity<>(firstCar.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/byColour", produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Car> getCarByColour(@RequestParam String colour){
        Optional<Car> firstCar = carsList.stream().filter(car -> car.getColour().equals(colour)).findFirst();
        if(firstCar.isPresent()){
            return new ResponseEntity<>(firstCar.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity addCar(@RequestBody Car car){
        boolean add = carsList.add(car);
        if(add){
            return new ResponseEntity(HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping
    public ResponseEntity modCar(@RequestBody Car car){
        Optional<Car> firstCar = carsList.stream().filter(carFromList -> carFromList.getId() == car.getId()).findFirst();
        if(firstCar.isPresent()){
            carsList.remove(firstCar.get());
            carsList.add(car);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{id}/{colour}")
    public ResponseEntity modCarElement(@PathVariable long id, @PathVariable String colour){
        Optional<Car> firstCar = carsList.stream().filter(carFromList -> carFromList.getId() == id).findFirst();
        if(firstCar.isPresent()){
           firstCar.get().setColour(colour);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeCar(@PathVariable long id){
        Optional<Car> firstCar = carsList.stream().filter(carFromList -> carFromList.getId() == id).findFirst();
        if(firstCar.isPresent()){
           carsList.remove(firstCar.get());
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}
