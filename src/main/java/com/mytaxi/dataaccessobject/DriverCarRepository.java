package com.mytaxi.dataaccessobject;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.DriverCarDO;

@Repository
public interface DriverCarRepository extends CrudRepository<DriverCarDO, Long>
{
    DriverCarDO findByDriverIdAndCarId(final Long driverId, final Long carId);
    
    DriverCarDO findByCarId(final Long carId);
    
    @Query("SELECT D, C FROM CarDO C, DriverDO D, DriverCarDO DC " +
        "WHERE DC.carId = C.id AND D.id = DC.driverId " +
        "AND (C.seatCount = :#{#driverDTO.car.seatCount} "
        + "OR C.convertible = :#{#driverDTO.car.convertible} "
        + "OR C.licensePlate = :#{#driverDTO.car.licensePlate} " 
        + "OR C.engineType = :#{#driverDTO.car.engineType} "
        + "OR C.color = :#{#driverDTO.car.color} "
        + "OR C.model = :#{#driverDTO.car.model} "
        + "OR D.username = :#{#driverDTO.username} "
        + "OR D.onlineStatus = :#{#driverDTO.onlineStatus} "
        + "OR D.id = :#{#driverDTO.id} "
        + "OR C.id = :#{#driverDTO.car.id}) "
        )
    List<Object[]> findDriversByAttributes(@Param("driverDTO") final DriverDTO driverDTO);

}
