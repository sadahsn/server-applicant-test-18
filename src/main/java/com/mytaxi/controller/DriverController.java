package com.mytaxi.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mytaxi.controller.mapper.DriverMapper;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverCarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.driver.DriverService;
import com.mytaxi.service.drivercar.DriverCarService;

/**
 * All operations with a driver will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/drivers")
public class DriverController
{

    private final DriverService driverService;

    private final DriverCarService driverCarService;


    @Autowired
    public DriverController(final DriverService driverService, DriverCarService driverCarService)
    {
        this.driverService = driverService;
        this.driverCarService = driverCarService;
    }


    @GetMapping("/{driverId}")
    public DriverDTO getDriver(@PathVariable Long driverId) throws EntityNotFoundException
    {
        return DriverMapper.makeDriverDTO(driverService.findDriver(driverId));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DriverDTO createDriver(@Valid @RequestBody DriverDTO driverDTO) throws ConstraintsViolationException
    {
        DriverDO driverDO = DriverMapper.makeDriverDO(driverDTO);
        return DriverMapper.makeDriverDTO(driverService.create(driverDO));
    }


    @DeleteMapping("/{driverId}")
    public void deleteDriver(@PathVariable long driverId) throws EntityNotFoundException
    {
        driverService.delete(driverId);
    }


    @PutMapping("/{driverId}")
    public void updateLocation(
        @PathVariable long driverId, @RequestParam double longitude, @RequestParam double latitude)
        throws EntityNotFoundException
    {
        driverService.updateLocation(driverId, longitude, latitude);
    }


    @GetMapping
    public List<DriverDTO> findDrivers(@RequestParam OnlineStatus onlineStatus)
    {
        return DriverMapper.makeDriverDTOList(driverService.find(onlineStatus));
    }


    @PostMapping("/selectCar")
    public DriverDTO selectCar(@RequestParam Long driverId, @RequestParam Long carId) throws EntityNotFoundException, CarAlreadyInUseException
    {
        DriverCarDO driverCar = driverCarService.selectCar(driverId, carId);
        DriverDO driver = driverService.findDriver(driverId);
        CarDO car = driverCarService.findCar(carId);
        BeanUtils.copyProperties(driverCar, driver);
        
        return DriverMapper.makeDriverDTO(driver, car);
    }


    @DeleteMapping("/deselectCar")
    public void deSelectCar(@RequestParam Long driverId, @RequestParam Long carId) throws EntityNotFoundException
    {
        driverCarService.deSelectCar(driverId, carId);
    }
    
    @GetMapping("/filterDrivers")
    public List<Object[]> filterDriverByAttributes(@RequestParam Map<String, String> params)
    {
        return driverCarService.findDriverByAttributes(DriverMapper.mapRequestParamsToDriverDTO(params));
    }

}
