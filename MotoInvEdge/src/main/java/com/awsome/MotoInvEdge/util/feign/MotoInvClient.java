package com.awsome.MotoInvEdge.util.feign;

import com.awsome.MotoInvEdge.model.Motorcycle;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("moto-inventory-service")
public interface MotoInvClient {

    @RequestMapping(value = "/motorcycle", method = RequestMethod.POST)
    public Motorcycle createMotorcycle(@RequestBody Motorcycle motorcycle);

    @RequestMapping(value = "/motorcycle/{id}", method = RequestMethod.GET)
    public Motorcycle getMotorcycle(@PathVariable(name = "id") int id);

    @RequestMapping(value = "/motorcycle", method = RequestMethod.GET)
    public List<Motorcycle> getAllMotorcycles();

    @RequestMapping(value = "/motorcycle", method = RequestMethod.PUT)
    public void updateMotorcycle(@RequestBody Motorcycle motorcycle);

    @RequestMapping(value = "/motorcycle/{id}", method = RequestMethod.DELETE)
    public void deleteMotorcycle(@PathVariable(name = "id") int id);
}
