package com.awsome.MotoInvEdge.service;

import com.awsome.MotoInvEdge.model.Motorcycle;
import com.awsome.MotoInvEdge.util.feign.MotoInvClient;
import com.awsome.MotoInvEdge.viewmodel.MotoViewModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class ServiceLayerTest {

    @InjectMocks
    private ServiceLayer serviceLayer;

    @Mock
    MotoInvClient client;

    private void motoInvClientMock() {
        Motorcycle motorcycle = new Motorcycle();
        motorcycle.setMake("dd");
        motorcycle.setModel("yy");
        motorcycle.setColor("ddff");
        motorcycle.setVin("sdafasdf");
        motorcycle.setYear("1999");
        motorcycle.setPrice(new BigDecimal("400.55"));
        motorcycle.setId(1);

        Motorcycle motorcycle1 = new Motorcycle();
        motorcycle1.setMake("dddsf");
        motorcycle1.setModel("yysdf");
        motorcycle1.setColor("ddfsdff");
        motorcycle1.setVin("sdafasdsdff");
        motorcycle1.setYear("1999");
        motorcycle1.setPrice(new BigDecimal("20000.88"));
        motorcycle1.setId(2);

        List<Motorcycle> motorcycleList = new ArrayList<>();
        motorcycleList.add(motorcycle);
        motorcycleList.add(motorcycle1);

        doReturn(motorcycle).when(client).getMotorcycle(1);
        doReturn(motorcycleList).when(client).getAllMotorcycles();
    }

    @Before
    public void setUp() throws Exception {
        motoInvClientMock();
    }

    @Test
    public void getAllMoto() {
        Motorcycle motorcycle = new Motorcycle();
        motorcycle.setMake("dd");
        motorcycle.setModel("yy");
        motorcycle.setColor("ddff");
        motorcycle.setVin("sdafasdf");
        motorcycle.setYear("1999");
        motorcycle.setPrice(new BigDecimal("400.55"));
        motorcycle.setId(1);

        Motorcycle motorcycle1 = new Motorcycle();
        motorcycle1.setMake("dddsf");
        motorcycle1.setModel("yysdf");
        motorcycle1.setColor("ddfsdff");
        motorcycle1.setVin("sdafasdsdff");
        motorcycle1.setYear("1999");
        motorcycle1.setPrice(new BigDecimal("20000.88"));
        motorcycle1.setId(2);

        List<Motorcycle> motorcycleList = serviceLayer.getAllMoto();

        assertEquals(2, motorcycleList.size());
        assertTrue(motorcycleList.contains(motorcycle));
        assertTrue(motorcycleList.contains(motorcycle1));
    }

    @Test
    public void buyMoto() {
        Motorcycle motorcycle = new Motorcycle();
        motorcycle.setMake("dd");
        motorcycle.setModel("yy");
        motorcycle.setColor("ddff");
        motorcycle.setVin("sdafasdf");
        motorcycle.setYear("1999");
        motorcycle.setPrice(new BigDecimal("400.55"));
        motorcycle.setId(1);

        MotoViewModel expectedMvm = new MotoViewModel();
        expectedMvm.setMotorcycle(motorcycle);
        expectedMvm.setSalesTax(new BigDecimal("27.03"));
        expectedMvm.setTransportCost(new BigDecimal("395"));
        expectedMvm.setDocumentFee(new BigDecimal("234"));
        expectedMvm.setTotalCost(new BigDecimal("1056.58"));

        MotoViewModel mvm = serviceLayer.buyMoto(motorcycle);
        System.out.println(expectedMvm);
        System.out.println(mvm);
        assertEquals(expectedMvm, mvm);
    }
}