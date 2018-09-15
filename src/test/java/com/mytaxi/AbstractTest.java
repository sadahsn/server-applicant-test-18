package com.mytaxi;

import java.time.ZonedDateTime;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.DriverCarDTO;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverCarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.EngineType;
import com.mytaxi.domainvalue.GeoCoordinate;
import com.mytaxi.domainvalue.OnlineStatus;

/**
 * Created by vinodjagwani on 7/15/17.
 */
@RunWith(MockitoJUnitRunner.class)
public abstract class AbstractTest
{

    public CarDO getCar()
    {
        CarDO car = new CarDO();
        car.setId(1L);
        car.setSeatCount(2);
        car.setRating(11.0F);
        car.setDateCreated(ZonedDateTime.now());
        car.setLicensePlate("ABV101");
        car.setEngineType(EngineType.ELECTRIC);
        car.setConvertible(true);
        car.setManufacturer("Audi");
        car.setModel("2017");
        car.setColor("red");
        return car;
    }




    public CarDTO getCarData()
    {
        return CarDTO.builder()
            .convertible(true)
            .engineType(EngineType.ELECTRIC.name())
            .licensePlate("ABV101")
            .seatCount(2)
            .manufacturer("Audi")
            .rating(11.0F)
            .build();
    }


    public DriverDO getDriver()
    {
        DriverDO driver = new DriverDO();
        driver.setId(1L);
        driver.setDateCreated(ZonedDateTime.now());
        driver.setDeleted(false);
        driver.setUsername("test");
        driver.setPassword("test");
        driver.setOnlineStatus(OnlineStatus.ONLINE);
        GeoCoordinate geoCoordinate = new GeoCoordinate(90, 90);
        driver.setCoordinate(geoCoordinate);
        return driver;
    }


    public DriverDTO getDriverData()
    {
        GeoCoordinate geoCoordinate = new GeoCoordinate(90, 90);
        return DriverDTO.newBuilder()
            .setId(1L)
            .setPassword("test")
            .setUsername("test")
            .setCoordinate(geoCoordinate)
            .setCarDto(getCarData())
            .createDriverDTO();
    }


    public DriverCarDO getDriverCar()
    {
        DriverCarDO driverCar = new DriverCarDO();
        driverCar.setCarId(1L);
        driverCar.setDriverId(1L);
        return driverCar;
    }
    
    public DriverCarDTO getDriverCarData()
    {
        DriverCarDTO driverCar = DriverCarDTO.builder().carId(1L).driverId(1L).build();
        return driverCar;
    }
}
