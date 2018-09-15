package com.mytaxi.dataaccessobject;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.google.common.collect.Lists;
import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.domainobject.CarDO;

public class CarRepositoryTest extends AbstractRepositoryTest
{

    @Autowired
    private CarRepository carRepository;


    @Test
    public void testDriverById()
    {
        Optional<CarDO> car = carRepository.findById(1L);
        Assert.assertNotNull(car.get());
    }


    @Test
    public void testAllCars()
    {
        List<CarDO> cars = Lists.newArrayList(carRepository.findAll());
        Assert.assertThat(cars, hasSize(3));
    }

}
