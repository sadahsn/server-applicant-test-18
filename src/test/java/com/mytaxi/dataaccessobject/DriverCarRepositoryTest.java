package com.mytaxi.dataaccessobject;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import com.mytaxi.dataaccessobject.DriverCarRepository;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.DriverCarDTO;
import com.mytaxi.domainobject.DriverCarDO;


public class DriverCarRepositoryTest extends AbstractRepositoryTest
{
   
    

    @Autowired
    private DriverCarRepository driverCarRepository;


    @Test
    public void testFindByDriverIdAndCarId()
    {
        DriverCarDO driverCar = getDriverCar();
        driverCarRepository.save(driverCar);
        DriverCarDO savedDriverCar = driverCarRepository.findByDriverIdAndCarId(1L, 1L);
        Assert.assertNotNull(savedDriverCar);
    }


    @Test
    public void testAlreadyExistCarWithDriver()
    {
        DriverCarDO driverCar1 = getDriverCar();
        DriverCarDO driverCar2 = getDriverCar();
        driverCarRepository.save(driverCar1);
        DriverCarDO savedDriverCar = driverCarRepository.findByDriverIdAndCarId(1L, 1L);
        driverCarRepository.save(driverCar2);
        Assert.assertNotNull(savedDriverCar);
    }

    private DriverCarDO getDriverCar()
    {
        DriverCarDO driverCar = new DriverCarDO();
        driverCar.setDriverId(1L);
        driverCar.setCarId(1L);
        return driverCar;
    }

}
