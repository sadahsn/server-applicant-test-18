package com.mytaxi.dataaccessobject;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mytaxi.domainobject.DriverCarDO;

@Repository
public interface DriverCarRepository extends CrudRepository<DriverCarDO, Long>
{

}
