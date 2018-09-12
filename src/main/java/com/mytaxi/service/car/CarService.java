package com.mytaxi.service.car;


import java.util.List;

import javax.validation.Valid;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;

public interface CarService
{
    public CarDO findCarById(Long id) throws EntityNotFoundException;
    
    public List<CarDO> getAllCars();
    
    public CarDO createCar(CarDO carDO) throws ConstraintsViolationException;
    
    public void updateCar(CarDO carDO) throws EntityNotFoundException;
    
    void deleteCar(Long carId) throws EntityNotFoundException;

    public void update(@Valid CarDTO carDto) throws EntityNotFoundException;
}
