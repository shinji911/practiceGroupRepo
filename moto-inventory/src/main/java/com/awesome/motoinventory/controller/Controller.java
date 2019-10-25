package com.awesome.motoinventory.controller;
import com.awesome.motoinventory.dao.MotorcycleDao;
import com.awesome.motoinventory.model.Motorcycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class Controller {
    // Properties
    @Autowired
    private MotorcycleDao motorcycleDao;

    // Constructors
    public Controller(MotorcycleDao motorcycleDao) {
        this.motorcycleDao = motorcycleDao;
    }

    @PostMapping(value = "/motorcycle")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Motorcycle addMotorcycle(@RequestBody @Valid Motorcycle motorcycle) {
        return motorcycleDao.addMotorcycle(motorcycle);
    }

    @GetMapping(value = "/motorcycle/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Motorcycle getMotorcycle(@PathVariable int id) {
        return motorcycleDao.getMotorcycle(id);
    }

    @GetMapping(value = "/motorcycle")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Motorcycle> getAllMotorcycle() {
        return motorcycleDao.getAllMotorcycle();
    }

    @PutMapping(value = "/motorcycle")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateMotorcycle(@RequestBody @Valid Motorcycle motorcycle) {
        motorcycleDao.updateMotorcycle(motorcycle);
    }

    @DeleteMapping(value = "/motorcycle/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteMotorcycle(@PathVariable int id) {
        motorcycleDao.deleteMotorcycle(id);
    }
}
