package com.awsome.MotoInvEdge.service;

import com.awsome.MotoInvEdge.model.Motorcycle;
import com.awsome.MotoInvEdge.util.feign.MotoInvClient;
import com.awsome.MotoInvEdge.viewmodel.MotoViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
public class ServiceLayer {

    @Autowired
    MotoInvClient client;

    public List<Motorcycle> getAllMoto() {
        return client.getAllMotorcycles();
    }

    public MotoViewModel buyMoto(Motorcycle motorcycle) {
        motorcycle = client.getMotorcycle(motorcycle.getId());
        MotoViewModel mvm = new MotoViewModel();
        mvm.setMotorcycle(motorcycle);
        mvm.setSalesTax(motorcycle.getPrice().multiply(new BigDecimal("0.0675")).setScale(2, RoundingMode.FLOOR));
        mvm.setDocumentFee(new BigDecimal("234"));
        if (motorcycle.getPrice().intValue() < 9999) {
            mvm.setTransportCost(new BigDecimal("395"));
        } else {
            mvm.setTransportCost(new BigDecimal("499"));
        }
        mvm.setTotalCost(motorcycle.getPrice().add(mvm.getSalesTax()).add(mvm.getDocumentFee()).add(mvm.getTransportCost()));

        return mvm;
    }
}
