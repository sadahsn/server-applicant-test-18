package com.mytaxi.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.Collections;
import java.util.List;

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
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.service.car.CarService;


public class CarControllerTest extends AbstractTest
{

    private static final ObjectMapper mapper = new ObjectMapper();

    private MockMvc mvc;

    @InjectMocks
    private CarController carController;
    
    @Mock
    private CarService carService;


    @BeforeClass
    public static void setUp()
    {
        MockitoAnnotations.initMocks(CarController.class);
    }


    @Before
    public void init()
    {
        mvc = MockMvcBuilders.standaloneSetup(carController).dispatchOptions(true).build();
    }


    @Test
    public void testGetCarById() throws Exception
    {
        CarDO car = getCar();
        doReturn(car).when(carService).findCarById(any(Long.class));
        carController.getCarById(1L);
        MvcResult result = mvc
            .perform(get("/v1/cars/{carId}", 1))
            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        final String responseBody = result.getResponse().getContentAsString();
        Assert.assertTrue(responseBody.contains("ABV101"));
    }


    @Test
    public void testGetAllCars() throws Exception
    {
        List<CarDO> cars = Collections.singletonList(getCar());
        doReturn(cars).when(carService).getAllCars();
        carController.getAllCars();
        MvcResult result = mvc
            .perform(get("/v1/cars/AllCars"))
            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        final String responseBody = result.getResponse().getContentAsString();
        Assert.assertTrue(responseBody.contains("ABV101"));
    }


    @Test
    public void testCreateCar() throws Exception
    {
        CarDTO CarDto = getCarData();
        CarDO car = getCar();
        String jsonInString = mapper.writeValueAsString(car);
        doReturn(car).when(carService).createCar(any(CarDO.class));
        carController.createCar(CarDto);
        MvcResult result = mvc
            .perform(post("/v1/cars/createCar")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(jsonInString))
            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        final String responseBody = result.getResponse().getContentAsString();
        Assert.assertTrue(responseBody.contains("11.0"));
    }


    @Test
    public void testUpdateCar() throws Exception
    {
        CarDTO carData = getCarData();
        String jsonInString = mapper.writeValueAsString(carData);
        doNothing().when(carService).update(any(CarDTO.class));
        carController.updateCar(carData);
        MvcResult result = mvc
            .perform(put("/v1/cars/updateCar")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(jsonInString))
            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        Assert.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }


    @Test
    public void testDeleteCar() throws Exception
    {
        doNothing().when(carService).deleteCar(any(Long.class));
        carController.deleteCar(1L);
        MvcResult result = mvc
            .perform(delete("/v1/cars/{carId}", 1L))
            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        Assert.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

}
