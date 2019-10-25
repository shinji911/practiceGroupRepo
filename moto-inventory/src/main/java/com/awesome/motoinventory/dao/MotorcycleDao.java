package com.awesome.motoinventory.dao;
import com.awesome.motoinventory.model.Motorcycle;
import java.util.List;

public interface MotorcycleDao {
    Motorcycle addMotorcycle(Motorcycle motorcycle);
    Motorcycle getMotorcycle(Integer id);
    List<Motorcycle> getAllMotorcycle();
    void updateMotorcycle(Motorcycle motorcycle);
    void deleteMotorcycle(Integer id);
}
