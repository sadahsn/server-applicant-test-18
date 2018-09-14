package com.mytaxi.service.drivercar;

import java.util.List;

import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverCarDO;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.EntityNotFoundException;

public interface DriverCarService
{

    DriverCarDO selectCar(Long driverId, Long carId) throws EntityNotFoundException, CarAlreadyInUseException;


    void deSelectCar(Long driverId, Long carId) throws EntityNotFoundException;


    void delete(DriverCarDO driverCarDo);


    DriverCarDO save(DriverCarDO driverCarDo);


    List<Object[]> findDriverByAttributes(DriverDTO driverDto);


    DriverCarDO findByDriverIdAndCarId(Long driverId, Long carId);


    CarDO findCar(Long carId) throws EntityNotFoundException;

}
