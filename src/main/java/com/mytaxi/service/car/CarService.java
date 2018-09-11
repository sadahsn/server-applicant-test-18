package com.mytaxi.service.car;


import com.mytaxi.domainobject.CarDO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;

public interface CarService
{
    public CarDO findCarById(Long id) throws EntityNotFoundException;
    
    public CarDO createCar(CarDO carDO) throws ConstraintsViolationException;
    
    public void updateCar(CarDO carDO) throws EntityNotFoundException;
    
    void deleteCar(Long carId) throws EntityNotFoundException;
}
