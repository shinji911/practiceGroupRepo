package com.awsome.MotoInvEdge.controlller;

import com.awsome.MotoInvEdge.model.Motorcycle;
import com.awsome.MotoInvEdge.service.ServiceLayer;
import com.awsome.MotoInvEdge.viewmodel.MotoViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
public class MotoController {

    @Autowired
    ServiceLayer serviceLayer;

    @RequestMapping(value = "/moto", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public MotoViewModel buyMotorcycle(@RequestBody @Valid Motorcycle motorcycle) {
        return serviceLayer.buyMoto(motorcycle);
    }

    @RequestMapping(value = "/moto", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Motorcycle> getAllMotorcycles() {
        return serviceLayer.getAllMoto();
    }

}
