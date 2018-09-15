package com.mytaxi.dataaccessobject;

import com.mytaxi.dataaccessobject.DriverRepository;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import java.util.List;
import java.util.Optional;

public class DriverRepositoryTest  extends AbstractRepositoryTest
{
    
    private static final String USER_NAME = "driver02";


    @Autowired
    private DriverRepository driverRepository;


    @Test
    public void testDriverById()
    {
        Optional<DriverDO> driver = driverRepository.findById(1L);
        Assert.assertNotNull(driver.get());
    }


    @Test
    public void testDriverByOnlineStatus()
    {
        List<DriverDO> onlineDrivers = driverRepository.findByOnlineStatus(OnlineStatus.ONLINE);
        Assert.assertThat(onlineDrivers, hasSize(4));
    }


    @Test
    public void testDriverByOfflineStatus()
    {
        List<DriverDO> offlineDrivers = driverRepository.findByOnlineStatus(OnlineStatus.OFFLINE);
        Assert.assertThat(offlineDrivers, hasSize(4));
    }


    @Test
    public void testDriverByUsername()
    {
        DriverDO driver = driverRepository.findByUsername(USER_NAME);
        Assert.assertNotNull(driver);
    }
}
