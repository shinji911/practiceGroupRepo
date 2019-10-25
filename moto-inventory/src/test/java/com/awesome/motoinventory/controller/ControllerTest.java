package com.awesome.motoinventory.controller;
import com.awesome.motoinventory.dao.MotorcycleDao;
import com.awesome.motoinventory.model.Motorcycle;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(Controller.class)
public class ControllerTest {
    // Properties
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MotorcycleDao motorcycleDao;
    private ObjectMapper objectMapper = new ObjectMapper();

    // Tests
    @Test
    public void test_addMotorcycle_ShouldReturnCreatedMotorcycle_andReturn200() throws Exception {
        // Create an inputMotorcycle object
        Motorcycle inputMotorcycle = new Motorcycle(new BigDecimal("4999.99"), "12345", "Yamada", "ZX200", "1999", "Blue");
        String inputJson = objectMapper.writeValueAsString(inputMotorcycle);

        // Create a responseMotorcycle object
        Motorcycle responseMotorcycle = new Motorcycle(1, new BigDecimal("4999.99"), "12345", "Yamada", "ZX200", "1999", "Blue");
        String responseJson = objectMapper.writeValueAsString(responseMotorcycle);

        // Mock the motorcycleDao
        Mockito.when(motorcycleDao.addMotorcycle(inputMotorcycle)).thenReturn(responseMotorcycle);

        // Mock the MVC
        this.mockMvc.perform(post("/motorcycle")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(responseJson));
    }

    @Test
    public void test_addMotorcycle_missingPrice_ShouldReturn422() throws Exception {
        // Create an inputMotorcycle object
        Motorcycle inputMotorcycle = new Motorcycle();
        inputMotorcycle.setVin("123");
        inputMotorcycle.setMake("123");
        inputMotorcycle.setModel("123");
        inputMotorcycle.setYear("1222");
        inputMotorcycle.setColor("Blue");
        String inputJson = objectMapper.writeValueAsString(inputMotorcycle);

        // Mock the MVC
        this.mockMvc.perform(post("/motorcycle")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void test_addMotorcycle_missingVin_ShouldReturn422() throws Exception {
        // Create an inputMotorcycle object
        Motorcycle inputMotorcycle = new Motorcycle();
        inputMotorcycle.setPrice(new BigDecimal("1999.99"));
        inputMotorcycle.setMake("123");
        inputMotorcycle.setModel("123");
        inputMotorcycle.setYear("1222");
        inputMotorcycle.setColor("Blue");
        String inputJson = objectMapper.writeValueAsString(inputMotorcycle);

        // Mock the MVC
        this.mockMvc.perform(post("/motorcycle")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void test_addMotorcycle_missingMake_ShouldReturn422() throws Exception {
        // Create an inputMotorcycle object
        Motorcycle inputMotorcycle = new Motorcycle();
        inputMotorcycle.setPrice(new BigDecimal("1999.99"));
        inputMotorcycle.setVin("123");
        inputMotorcycle.setModel("123");
        inputMotorcycle.setYear("1222");
        inputMotorcycle.setColor("Blue");
        String inputJson = objectMapper.writeValueAsString(inputMotorcycle);

        // Mock the MVC
        this.mockMvc.perform(post("/motorcycle")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void test_addMotorcycle_missingModel_ShouldReturn422() throws Exception {
        // Create an inputMotorcycle object
        Motorcycle inputMotorcycle = new Motorcycle();
        inputMotorcycle.setPrice(new BigDecimal("1999.99"));
        inputMotorcycle.setVin("123");
        inputMotorcycle.setMake("123");
        inputMotorcycle.setYear("1222");
        inputMotorcycle.setColor("Blue");
        String inputJson = objectMapper.writeValueAsString(inputMotorcycle);

        // Mock the MVC
        this.mockMvc.perform(post("/motorcycle")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void test_addMotorcycle_missingYear_ShouldReturn422() throws Exception {
        // Create an inputMotorcycle object
        Motorcycle inputMotorcycle = new Motorcycle();
        inputMotorcycle.setPrice(new BigDecimal("1999.99"));
        inputMotorcycle.setVin("123");
        inputMotorcycle.setMake("123");
        inputMotorcycle.setModel("123");
        inputMotorcycle.setColor("Blue");
        String inputJson = objectMapper.writeValueAsString(inputMotorcycle);

        // Mock the MVC
        this.mockMvc.perform(post("/motorcycle")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void test_addMotorcycle_missingColor_ShouldReturn422() throws Exception {
        // Create an inputMotorcycle object
        Motorcycle inputMotorcycle = new Motorcycle();
        inputMotorcycle.setPrice(new BigDecimal("1999.99"));
        inputMotorcycle.setVin("123");
        inputMotorcycle.setMake("123");
        inputMotorcycle.setModel("123");
        inputMotorcycle.setYear("1999");
        String inputJson = objectMapper.writeValueAsString(inputMotorcycle);

        // Mock the MVC
        this.mockMvc.perform(post("/motorcycle")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void test_getMotorcycle_ShouldReturnRequestedMotorcycleObject_andReturn200() throws Exception {
        // Create a responseMotorcycle object
        Motorcycle responseMotorcycle = new Motorcycle(1, new BigDecimal("4999.99"), "12345", "Yamada", "ZX200", "1999", "Blue");
        String responseJson = objectMapper.writeValueAsString(responseMotorcycle);

        // Mock the motorcycleDao
        Mockito.when(motorcycleDao.getMotorcycle(1)).thenReturn(responseMotorcycle);

        // Mock the MVC
        this.mockMvc.perform(get("/motorcycle/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(responseJson));
    }

    @Test
    public void test_getAllMotorcycle_ShouldReturnAListOfMotorcycles_andReturn200() throws Exception {
        // Create a responseMotorcycle objects
        Motorcycle responseMotorcycle1 = new Motorcycle(1, new BigDecimal("4999.99"), "12345", "Yamada", "ZX200", "1999", "Blue");
        Motorcycle responseMotorcycle2 = new Motorcycle(2, new BigDecimal("1999.99"), "ABC", "Suzuki", "5HE3", "2018", "Red");
        List<Motorcycle> motorcycleList = new ArrayList<>();
        motorcycleList.add(responseMotorcycle1);
        motorcycleList.add(responseMotorcycle2);
        String responseJson = objectMapper.writeValueAsString(motorcycleList);

        // Mock the motorcycleDao
        Mockito.when(motorcycleDao.getAllMotorcycle()).thenReturn(motorcycleList);

        // Mock the MVC
        this.mockMvc.perform(get("/motorcycle"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(responseJson));
    }

    @Test
    public void test_updateMotorcycle_ShouldUpdateAMotorcycleObject_andReturn200() throws Exception {
        // Create a responseMotorcycle object
        Motorcycle inputMotorcycle = new Motorcycle(1, new BigDecimal("4999.99"), "12345", "Yamada", "ZX200", "1999", "Blue");
        String inputJson = objectMapper.writeValueAsString(inputMotorcycle);

        // Mock the motorcycleDao
        Mockito.doNothing().when(motorcycleDao).updateMotorcycle(inputMotorcycle);

        // Mock the MVC
        this.mockMvc.perform(put("/motorcycle")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void test_deleteMotorcycle_ShouldDeleteAMotorcycleObject_andReturn200() throws Exception {
        // Create a responseMotorcycle object
        Motorcycle inputMotorcycle = new Motorcycle(1, new BigDecimal("4999.99"), "12345", "Yamada", "ZX200", "1999", "Blue");

        // Mock the motorcycleDao
        Mockito.doNothing().when(motorcycleDao).deleteMotorcycle(1);

        // Mock the MVC
        this.mockMvc.perform(delete("/motorcycle/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
