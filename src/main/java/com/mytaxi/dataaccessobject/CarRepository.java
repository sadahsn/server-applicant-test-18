package com.mytaxi.dataaccessobject;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mytaxi.domainobject.CarDO;

/**
 * Data access repository for car
 */
@Repository
public interface CarRepository extends CrudRepository<CarDO, Long>
{

}
