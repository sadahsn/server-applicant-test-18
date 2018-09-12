package com.mytaxi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.car.CarService;

@RestController
@RequestMapping("v1/cars")
public class CarController
{
    private final CarService carService;


    @Autowired
    public CarController(CarService carService)
    {
        this.carService = carService;
    }


    @GetMapping("/{carId}")
    public CarDTO getCarById(@PathVariable long carId) throws EntityNotFoundException
    {
        CarDTO car = new CarDTO();
        BeanUtils.copyProperties(carService.findCarById(carId), car);
        return car;
    }


    @PostMapping("/createCar")
    public ResponseEntity<CarDTO> createCar(@RequestBody CarDTO carDto) throws ConstraintsViolationException
    {
        CarDO car = new CarDO();
        BeanUtils.copyProperties(carDto, car);
        carService.createCar(car);
        return new ResponseEntity<CarDTO>(carDto, HttpStatus.OK);

    }


    @DeleteMapping("/{carId}")
    public ResponseEntity<CarDTO> deleteCar(@PathVariable long carId) throws EntityNotFoundException
    {
        carService.deleteCar(carId);
        return new ResponseEntity(HttpStatus.OK);
    }


    @GetMapping("/AllCars")
    public ResponseEntity<List<CarDTO>> getAllCars()
    {
        List<CarDO> carsDo = carService.getAllCars();
        List<CarDTO> CarsDTO = new ArrayList<CarDTO>();

        carsDo.stream().forEach(carDo -> {
            CarDTO carDto = new CarDTO();
            BeanUtils.copyProperties(carDo, carDto);
            CarsDTO.add(carDto);
        });
        return new ResponseEntity<List<CarDTO>>(CarsDTO, HttpStatus.OK);
    }


    @PutMapping("/updateCar")
    public ResponseEntity<Void> updateCar(@Valid @RequestBody CarDTO carDto) throws EntityNotFoundException
    {
        carService.update(carDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
