package com.mytaxi.service.drivercar;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytaxi.dataaccessobject.DriverCarRepository;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverCarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.car.CarService;
import com.mytaxi.service.driver.DriverService;

@Service
public class DefaultDriverCarService implements DriverCarService
{
    private DriverService driverService;
    private DriverCarRepository driverCarRepository;
    private CarService carService;


    @Autowired
    public DefaultDriverCarService(DriverService driverService, CarService carService, DriverCarRepository driverCarRepository)
    {
        this.driverService = driverService;
        this.carService = carService;
        this.driverCarRepository = driverCarRepository;
    }


    @Override
    @Transactional
    public DriverCarDO selectCar(Long driverId, Long carId) throws EntityNotFoundException, CarAlreadyInUseException
    {

        DriverCarDO driverCarDo = driverCarRepository.findByCarId(carId);
        if (null != driverCarDo)
        {
            throw new CarAlreadyInUseException("Requested car is already taken");
        }

        DriverCarDO driverCar = null; 
        try
        {
            CarDO car = carService.findCarById(carId);
            DriverDO driver = driverService.findDriver(driverId);

            if (null != driver && null != car && OnlineStatus.ONLINE.equals(driver.getOnlineStatus()))
            {
                driverCar = new DriverCarDO();
                driverCar.setDriverId(driver.getId());
                driverCar.setCarId(car.getId());
                driverCarRepository.save(driverCar);
            }
            else if (null != driver && null != car && OnlineStatus.OFFLINE.equals(driver.getOnlineStatus()))
            {
                throw new CarAlreadyInUseException("Driver is offline");
            }
        }
        catch (EntityNotFoundException e)
        {
            throw new EntityNotFoundException("Driver or Car data not found.");
        }

        return driverCar;
    }


    @Override
    @Transactional
    public void deSelectCar(Long driverId, Long carId) throws EntityNotFoundException
    {
        DriverDO driver;
        CarDO car;
        try
        {
            driver = driverService.findDriver(driverId);
            car = carService.findCarById(carId);
            if (null != driver && null != car && OnlineStatus.ONLINE.equals(driver.getOnlineStatus()))
            {
                DriverCarDO driverCar = driverCarRepository.findByDriverIdAndCarId(driver.getId(), car.getId());
                driverCarRepository.delete(driverCar);
            }
        }
        catch (EntityNotFoundException e)
        {
            throw new EntityNotFoundException("Car or Driver entity not found ");
        }
    }


    @Override
    public DriverCarDO findByDriverIdAndCarId(Long driverId, Long carId)
    {
        return driverCarRepository.findByDriverIdAndCarId(driverId, carId);
    }


    @Override
    public void delete(DriverCarDO driverCarDo)
    {
        driverCarRepository.delete(driverCarDo);
    }


    @Override
    public DriverCarDO save(DriverCarDO driverCarDo)
    {
        return driverCarRepository.save(driverCarDo);
    }


    @Override
    public List<Object[]> findDriverByAttributes(DriverDTO driverDto)
    {
        return driverCarRepository.findDriversByAttributes(driverDto);
    }


    @Override
    public CarDO findCar(Long carId) throws EntityNotFoundException
    {
        return carService.findCarById(carId);
    }

}
