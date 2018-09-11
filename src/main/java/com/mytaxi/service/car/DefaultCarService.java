package com.mytaxi.service.car;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;

@Service
public class DefaultCarService implements CarService
{
 
    @Autowired
    private CarRepository carRepo;
    
    @Override
    public CarDO findCarById(Long id) throws EntityNotFoundException
    {
        CarDO car = findCar(id);
        return car;
    }

    @Override
    public CarDO createCar(CarDO carDo) throws ConstraintsViolationException
    {
        return carRepo.save(carDo);
    }

    @Override
    @Transactional
    public void updateCar(CarDO carDo) throws EntityNotFoundException
    {
        CarDO car = findCar(carDo.getId());
        car.setColor(carDo.getColor());
        car.setConvertible(carDo.isConvertible());
        car.setEngineType(carDo.getEngineType());
        car.setLicensePlate(carDo.getLicensePlate());
        car.setModel(carDo.getModel());
        car.setRating(carDo.getRating());
        car.setSeatCount(carDo.getSeatCount());
        car.setManufacturer(carDo.getManufacturer());
    }

    @Override
    @Transactional
    public void deleteCar(Long carId) throws EntityNotFoundException
    {
        CarDO car = findCar(carId);
        car.setDeleted(Boolean.TRUE);
    }
    
    private CarDO findCar(final Long carId) throws EntityNotFoundException
    {
        Optional<CarDO> car = carRepo.findById(carId);
        if (!car.isPresent())
        {
            throw new EntityNotFoundException("car with id: " + carId + " does not exist");
        }
        return car.get();
    }

}
