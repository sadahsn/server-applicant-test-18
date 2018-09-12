package com.mytaxi.service.drivercar;

import org.springframework.stereotype.Service;

import com.mytaxi.datatransferobject.DriverDTO;

@Service
public class DefaultDriverCar implements DriverCarService
{

    @Override
    public DriverDTO selectCar(Long driverId, Long carId)
    {
        return null;
    }

    @Override
    public void deSelectCar(Long driverId, Long carId)
    {
        
    }

}
