package com.mytaxi.service;

import com.mytaxi.AbstractTest;
import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.car.DefaultCarService;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

public class CarServiceTest extends AbstractTest
{
    

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private DefaultCarService carService;


    @BeforeClass
    public static void setUp()
    {
        MockitoAnnotations.initMocks(DefaultCarService.class);
    }


    @Test
    public void testFindCarById() throws EntityNotFoundException
    {
        CarDO car = getCar();
        when(carRepository.findById(any(Long.class))).thenReturn(Optional.of(car));
        carService.findCarById(any(Long.class));
        verify(carRepository, times(1)).findById(any(Long.class));
    }


    @Test
    public void testFindAllCars()
    {
        Iterable<CarDO> cars = Collections.singletonList(getCar());
        when(carRepository.findAll()).thenReturn(cars);
        carService.getAllCars();
        verify(carRepository, times(1)).findAll();
    }


    @Test
    public void testCreate() throws EntityNotFoundException, ConstraintsViolationException
    {
        CarDO car = getCar();
        when(carRepository.save(any(CarDO.class))).thenReturn(car);
        carService.createCar(car);
        verify(carRepository, times(1)).save(car);
    }


    @Test
    public void testUpdate() throws Exception
    {
        CarDO car = getCar();
        when(carRepository.findById(any(Long.class))).thenReturn(Optional.of(car));
        carService.updateCar(car);
        verify(carRepository, times(1)).findById(any(Long.class));
    }


    @Test
    public void testDelete() throws EntityNotFoundException
    {
        CarDO car = getCar();
        when(carRepository.findById(any(Long.class))).thenReturn(Optional.of(car));
        carService.deleteCar(1L);
        verify(carRepository, times(1)).findById(any(Long.class));
    }
}

