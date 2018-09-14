package com.mytaxi.controller.mapper;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.EngineType;
import com.mytaxi.domainvalue.GeoCoordinate;
import com.mytaxi.domainvalue.OnlineStatus;

import static org.apache.commons.collections.MapUtils.*;

public class DriverMapper
{
    public static DriverDO makeDriverDO(DriverDTO driverDTO)
    {
        return new DriverDO(driverDTO.getUsername(), driverDTO.getPassword());
    }


    public static DriverDTO makeDriverDTO(DriverDO driverDO)
    {
        DriverDTO.DriverDTOBuilder driverDTOBuilder = DriverDTO.newBuilder()
            .setId(driverDO.getId())
            .setPassword(driverDO.getPassword())
            .setUsername(driverDO.getUsername());

        GeoCoordinate coordinate = driverDO.getCoordinate();
        if (coordinate != null)
        {
            driverDTOBuilder.setCoordinate(coordinate);
        }

        return driverDTOBuilder.createDriverDTO();
    }


    public static DriverDTO makeDriverDTO(DriverDO driverDO, CarDO carDO)
    {
        CarDTO carDto = new CarDTO();
        BeanUtils.copyProperties(carDO, carDto);
        DriverDTO.DriverDTOBuilder driverDTOBuilder = DriverDTO.newBuilder()
            .setId(driverDO.getId())
            .setPassword(driverDO.getPassword())
            .setUsername(driverDO.getUsername())
            .setCarDto(carDto);

        GeoCoordinate coordinate = driverDO.getCoordinate();
        if (coordinate != null)
        {
            driverDTOBuilder.setCoordinate(coordinate);
        }

        return driverDTOBuilder.createDriverDTO();
    }


    public static List<DriverDTO> makeDriverDTOList(Collection<DriverDO> drivers)
    {
        return drivers.stream()
            .map(DriverMapper::makeDriverDTO)
            .collect(Collectors.toList());
    }
    
    public static DriverDTO mapRequestParamsToDriverDTO(Map<String, String> params) {
            CarDTO car= CarDTO.builder()
            .licensePlate(getString(params,"licensePlate"))
            .manufacturer(getString(params,"manufacturer"))
            .engineType(getString(params,"engineType"))
            .rating(getFloat(params,"rating"))
            .seatCount(getInteger(params,"seatCount"))
            .id(getLong(params,"carId"))
            .color(getString(params,"color"))
            .model(getString(params,"model"))
            .build();
            
           return DriverDTO.builder()
            .car(car)
            .onlineStatus(getString(params,"onlineStatus")==null?OnlineStatus.OFFLINE : OnlineStatus.valueOf(getString(params,"onlineStatus")))
            .username(getString(params,"username"))
            .id(getLong(params,"driverId"))
            .build();
               
    }
}

