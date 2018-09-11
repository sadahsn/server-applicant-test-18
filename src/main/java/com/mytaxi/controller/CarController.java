package com.mytaxi.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mytaxi.datatransferobject.CarDTO;

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


   /* @PostMapping
    public ResponseEntity<CarDTO> createCar(@RequestBody CarDTO){
        
        
    }*/
}
