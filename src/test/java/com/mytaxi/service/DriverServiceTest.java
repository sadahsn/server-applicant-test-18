package com.mytaxi.service;

import com.mytaxi.AbstractTest;
import com.mytaxi.dataaccessobject.DriverRepository;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.driver.DefaultDriverService;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class DriverServiceTest extends AbstractTest
{

    @Mock
    private DriverRepository driverRepository;

    @InjectMocks
    private DefaultDriverService driverService;


    @BeforeClass
    public static void setUp()
    {
        MockitoAnnotations.initMocks(DefaultDriverService.class);
    }


    @Test
    public void testFindByDriverId()throws EntityNotFoundException
    {
        DriverDO driver = getDriver();
        when(driverRepository.findById(any(Long.class))).thenReturn(Optional.of(driver));
        driverService.findDriver(any(Long.class));
        verify(driverRepository, times(1)).findById(any(Long.class));
    }


    @Test
    public void testCreate() throws ConstraintsViolationException
    {
        DriverDO driver = getDriver();
        when(driverRepository.save(any(DriverDO.class))).thenReturn(driver);
        driverService.create(driver);
        verify(driverRepository, times(1)).save(any(DriverDO.class));
    }


    @Test
    public void testDelete() throws EntityNotFoundException
    {
        DriverDO driver = getDriver();
        when(driverRepository.findById(any(Long.class))).thenReturn(Optional.of(driver));
        driverService.delete(any(Long.class));
        verify(driverRepository, times(1)).findById(any(Long.class));
    }


    @Test
    public void testUpdateLocation() throws EntityNotFoundException
    {
        DriverDO driver = getDriver();
        when(driverRepository.findById(any(Long.class))).thenReturn(Optional.of(driver));
        driverService.updateLocation(1L, 90.0, 90.0);
        verify(driverRepository, times(1)).findById(any(Long.class));
    }


    @Test
    public void testFindByOnlineStatus()
    {
        List<DriverDO> drivers = Collections.singletonList(getDriver());
        when(driverRepository.findByOnlineStatus(any(OnlineStatus.class))).thenReturn(drivers);
        driverService.find(OnlineStatus.ONLINE);
        verify(driverRepository, times(1)).findByOnlineStatus(any(OnlineStatus.class));
    }


}
