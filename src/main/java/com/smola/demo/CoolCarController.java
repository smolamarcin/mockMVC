package com.smola.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CoolCarController {
    private CarRepository carRepository;

    public CoolCarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }
    @GetMapping("/all")
    public List<Car> allCars(){
        return carRepository.findAll();
    }

    @GetMapping("/cool-cars")
    public Collection<Car> coolCars() {
        return carRepository.findAll().stream()
                .filter(this::isCool)
                .collect(Collectors.toList());
    }

    private boolean isCool(Car car) {
        return !car.getName().equals("AMC Gremlin") &&

                !car.getName().equals("Triumph Stag") &&

                !car.getName().equals("Ford Pinto") &&

                !car.getName().equals("Yugo GV");
    }
}
