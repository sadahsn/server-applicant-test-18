package com.mytaxi.service.drivercar;

import com.mytaxi.datatransferobject.DriverDTO;

public interface DriverCarService
{

    DriverDTO selectCar(Long driverId, Long carId);

    void deSelectCar(Long driverId, Long carId);

}
