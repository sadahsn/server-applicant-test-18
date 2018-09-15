package com.mytaxi.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytaxi.AbstractTest;
import com.mytaxi.controller.mapper.DriverMapper;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverCarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.service.driver.DriverService;
import com.mytaxi.service.drivercar.DriverCarService;

public class DriverControllerTest extends AbstractTest
{

    private static final ObjectMapper mapper = new ObjectMapper();

    private MockMvc mvc;

    @Mock
    private DriverService driverService;
    
    @Mock
    private DriverCarService driverCarService;

    @InjectMocks
    private DriverController driverController;


    @BeforeClass
    public static void setUp()
    {
        MockitoAnnotations.initMocks(DriverController.class);
    }


    @Before
    public void init()
    {
        mvc = MockMvcBuilders.standaloneSetup(driverController).dispatchOptions(true).build();
    }


    @Test
    public void testSelectCar() throws Exception
    {
        DriverCarDO driverCarData = getDriverCar();
        DriverDO driver = getDriver();
        CarDO car = getCar();
        
        doReturn(driverCarData).when(driverCarService).selectCar(any(Long.class), any(Long.class));
        doReturn(driver).when(driverService).findDriver(any(Long.class));
        doReturn(car).when(driverCarService).findCar(any(Long.class));
        
        driverController.selectCar(1L, 1L);
        MvcResult result = mvc
            .perform(post("/v1/drivers/selectCar")
                .param("driverId", "1")
                .param("carId", "1"))
            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        final String responseBody = result.getResponse().getContentAsString();
        Assert.assertTrue(responseBody.contains("test"));

    }


//    @Test
    public void testDeSelect() throws Exception
    {
        doNothing().when(driverCarService).deSelectCar(any(Long.class), any(Long.class));
        driverController.deSelectCar(1L, 1L);
        MvcResult result = mvc
            .perform(delete("/v1/drivers/deSelectCar")
                .param("driverId", "1")
                .param("carId", "1"))
            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        Assert.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }


    @Test
    public void testGetDriver() throws Exception
    {
        DriverDO driverData = getDriver();
        doReturn(driverData).when(driverService).findDriver(any(Long.class));
        driverController.getDriver(1L);
        MvcResult result = mvc
            .perform(get("/v1/drivers/{driverId}", 1))
            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        final String responseBody = result.getResponse().getContentAsString();
        Assert.assertTrue(responseBody.contains("test"));
    }


    @Test
    public void testUpdateLocation() throws Exception
    {
        doNothing().when(driverService).updateLocation(any(Long.class), any(Double.class), any(Double.class));
        driverController.updateLocation(1L, 99, 99);
        MvcResult result = mvc
            .perform(put("/v1/drivers/{driverId}", 1)
                .param("longitude", "99").param("latitude", "99"))
            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        Assert.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }


    @Test
    public void testCreateDriver() throws Exception
    {
        DriverDO driver = getDriver();
        DriverDTO driverData = getDriverData();
        String jsonInString = mapper.writeValueAsString(driverData);
        doReturn(driver).when(driverService).create(any(DriverDO.class));
        driverController.createDriver(driverData);
        MvcResult result = mvc
            .perform(post("/v1/drivers/createDriver")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonInString))
            .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
        final String responseBody = result.getResponse().getContentAsString();
        Assert.assertTrue(responseBody.contains("test"));

    }


//    @Test
    public void testFindDriverByAttributes() throws Exception
    {
        List<DriverDTO> driverData = Collections.singletonList(getDriverData());
        Map<String, String> params = new HashMap<>();
        params.put("username", "test");
        doReturn(driverData).when(driverCarService).findDriverByAttributes(DriverMapper.mapRequestParamsToDriverDTO(params));
        driverController.filterDriverByAttributes(params);
        MvcResult result = mvc
            .perform(get("/v1/drivers/filterDrivers?username=test"))
            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        final String responseBody = result.getResponse().getContentAsString();
        Assert.assertTrue(responseBody.contains(""));
    }


    @Test
    public void testDeleteDriver() throws Exception
    {
        doNothing().when(driverService).delete(any(Long.class));
        driverController.deleteDriver(1L);
        MvcResult result = mvc
            .perform(delete("/v1/drivers/{driverId}", 1))
            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        Assert.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }


    @Test
    public void testFindByOnlineStatus() throws Exception
    {
        List<DriverDO> driverData = Collections.singletonList(getDriver());
        doReturn(driverData).when(driverService).find(any(OnlineStatus.class));
        driverController.findDrivers(OnlineStatus.ONLINE);
        MvcResult result = mvc
            .perform(get("/v1/drivers/findDrivers")
                .param("onlineStatus", OnlineStatus.ONLINE.name()))
            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        final String responseBody = result.getResponse().getContentAsString();
        Assert.assertTrue(responseBody.contains("test"));
    }

}
