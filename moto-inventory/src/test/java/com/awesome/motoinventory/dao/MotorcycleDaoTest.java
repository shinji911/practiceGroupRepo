package com.awesome.motoinventory.dao;
import com.awesome.motoinventory.model.Motorcycle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class MotorcycleDaoTest {
    // Autowired Dao
    @Autowired
    private MotorcycleDao motorcycleDao;

    //private Motorcycle motorcycle2 = new Motorcycle(new BigDecimal("1999.99"), "ABC", "Suzuki", "5HE3", "2018", "Red");

    // setUp()
    @BeforeEach
    public void setUp() throws Exception {
        // Clean up the Motorcycle table
        List<Motorcycle> motorcycleList = motorcycleDao.getAllMotorcycle();
        motorcycleList.forEach(motorcycle -> motorcycleDao.deleteMotorcycle(motorcycle.getId()));
    }

    @Test
    public void addMotorcycle() {
        // Create a new motorcycle and add it to inventory
        Motorcycle motorcycle = new Motorcycle(new BigDecimal("4999.99"), "12345", "Yamada", "ZX200", "1999", "Blue");
        motorcycle = motorcycleDao.addMotorcycle(motorcycle);

        // Get a copy of the motorcycle in inventory
        Motorcycle motorcycleCopy = motorcycleDao.getMotorcycle(motorcycle.getId());

        // Test addMotorcycle() method
        assertEquals(motorcycle, motorcycleCopy);
    }

    @Test
    public void getMotorcycle() {
        // Create a new motorcycle and add it to inventory
        Motorcycle motorcycle = new Motorcycle(new BigDecimal("4999.99"), "12345", "Yamada", "ZX200", "1999", "Blue");
        motorcycle = motorcycleDao.addMotorcycle(motorcycle);

        // Get a copy of the motorcycle in inventory
        Motorcycle motorcycleCopy = motorcycleDao.getMotorcycle(motorcycle.getId());

        // Test getMotorcycle() method
        assertEquals(motorcycle, motorcycleCopy);
    }

    @Test
    public void getAllMotorcycle() {
        // Create 2 motorcycles and add them to inventory
        Motorcycle motorcycle1 = new Motorcycle(new BigDecimal("4999.99"), "12345", "Yamada", "ZX200", "1999", "Blue");
        Motorcycle motorcycle2 = new Motorcycle(new BigDecimal("1999.99"), "ABC", "Suzuki", "5HE3", "2018", "Red");
        motorcycle1 = motorcycleDao.addMotorcycle(motorcycle1);
        motorcycle2 = motorcycleDao.addMotorcycle(motorcycle2);

        // Get all motorcycles in inventory
        List<Motorcycle> motorcycleList = motorcycleDao.getAllMotorcycle();

        // Test getAllMotorcycle() method
        assertEquals(2, motorcycleList.size());

    }

    @Test
    public void updateMotorcycle() {
        // Create a new motorcycle and add it to inventory
        Motorcycle motorcycle = new Motorcycle(new BigDecimal("4999.99"), "12345", "Yamada", "ZX200", "1999", "Blue");
        motorcycle = motorcycleDao.addMotorcycle(motorcycle);

        // Update the motorcycle in inventory
        motorcycle.setColor("Update");
        motorcycleDao.updateMotorcycle(motorcycle);

        // Get a copy of the updated motorcycle item
        Motorcycle motorcycleCopy = motorcycleDao.getMotorcycle(motorcycle.getId());

        // Test updateMotorcycle() method
        assertEquals(motorcycle, motorcycleCopy);
    }

    @Test
    public void deleteMotorcycle() {
        // Create a new motorcycle and add it to inventory
        Motorcycle motorcycle = new Motorcycle(new BigDecimal("4999.99"), "12345", "Yamada", "ZX200", "1999", "Blue");
        motorcycle = motorcycleDao.addMotorcycle(motorcycle);

        // Delete the motorcycle item in the database
        motorcycleDao.deleteMotorcycle(motorcycle.getId());

        // Get a copy of the deleted motorcycle object
        Motorcycle motorcycleCopy = motorcycleDao.getMotorcycle(motorcycle.getId());

        // Test deleteMotorcycle() method
        assertNull(motorcycleCopy);
    }
}
