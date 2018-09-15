package com.mytaxi.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mytaxi.AbstractTest;
import com.mytaxi.dataaccessobject.DriverCarRepository;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.DriverCarDO;
import com.mytaxi.service.drivercar.DefaultDriverCarService;

public class DriverCarServiceTest extends AbstractTest
{
    

    @Mock
    private DriverCarRepository driverCarRepository;

    @InjectMocks
    private DefaultDriverCarService driverCarService;


    @BeforeClass
    public static void setUp()
    {
        MockitoAnnotations.initMocks(DefaultDriverCarService.class);
    }


    @Test
    public void testDelete()
    {
        DriverCarDO driverCar = getDriverCar();
        doNothing().when(driverCarRepository).delete(any(DriverCarDO.class));
        driverCarService.delete(driverCar);
        verify(driverCarRepository, times(1)).delete(any(DriverCarDO.class));
    }


    @Test
    public void testSave()
    {
        DriverCarDO driverCar = getDriverCar();
        when(driverCarRepository.save(any(DriverCarDO.class))).thenReturn(driverCar);
        driverCarService.save(driverCar);
        verify(driverCarRepository, times(1)).save(any(DriverCarDO.class));
    }


    @Test
    public void testFindByDriverIdAndCarId()
    {
        DriverCarDO driverCar = getDriverCar();
        when(driverCarRepository.findByDriverIdAndCarId(any(Long.class),any(Long.class))).thenReturn(driverCar);
        driverCarService.findByDriverIdAndCarId(1L, 1L);
        verify(driverCarRepository, times(1)).findByDriverIdAndCarId(any(Long.class),any(Long.class));
    }

    @Test
    public void  testFindDriverByCarAttributes()
    {
        DriverDTO driverData = getDriverData();
        List<Object[]> objects = new ArrayList<>();
        Object[] object = new Object[2];
        object[0] = getDriverCar();
        objects.add(object);
        driverCarService.findDriverByAttributes(getDriverData());
        verify(driverCarRepository, times(1)).findDriversByAttributes(any(DriverDTO.class));
    }
}
