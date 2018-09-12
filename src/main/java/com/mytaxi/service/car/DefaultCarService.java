package com.mytaxi.service.car;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainvalue.EngineType;
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
        car.setConvertible(carDo.getConvertible());
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


    @Override
    public List<CarDO> getAllCars()
    {
        Iterable<CarDO> carsList = carRepo.findAll();
        List<CarDO> allCars = new ArrayList<CarDO>();
        carsList.forEach(allCars::add);
        return allCars;
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


    @Override
    @Transactional
    public void update(@Valid CarDTO car) throws EntityNotFoundException
    {
        CarDO updateCar = findCar(car.getId());
        updateCar.setConvertible(car.getConvertible());
        updateCar.setEngineType(EngineType.valueOf(car.getEngineType()));
        updateCar.setLicensePlate(car.getLicensePlate());
        updateCar.setRating(car.getRating());
        updateCar.setSeatCount(car.getSeatCount());
        updateCar.setColor(car.getColor());
        updateCar.setModel(car.getModel());
        updateCar.setManufacturer(car.getManufacturer());
    }

}
