package com.awsome.MotoInvEdge.controlller;

import com.awsome.MotoInvEdge.model.Motorcycle;
import com.awsome.MotoInvEdge.service.ServiceLayer;
import com.awsome.MotoInvEdge.viewmodel.MotoViewModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MotoController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class MotoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceLayer serviceLayer;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void buyMotorcycleShouldReturnMotoViewModel() throws Exception {
        Motorcycle motorcycle = new Motorcycle();
        motorcycle.setMake("dd");
        motorcycle.setModel("yy");
        motorcycle.setColor("ddff");
        motorcycle.setVin("sdafasdf");
        motorcycle.setYear("1999");
        motorcycle.setPrice(new BigDecimal("400.55"));
        motorcycle.setId(1);

        String inputJson = mapper.writeValueAsString(motorcycle);

        MotoViewModel expectedMvm = new MotoViewModel();
        expectedMvm.setMotorcycle(motorcycle);
        expectedMvm.setSalesTax(new BigDecimal("27.03"));
        expectedMvm.setTransportCost(new BigDecimal("395"));
        expectedMvm.setDocumentFee(new BigDecimal("234"));
        expectedMvm.setTotalCost(new BigDecimal("1056.58"));

        String outputJson = mapper.writeValueAsString(expectedMvm);

        when(serviceLayer.buyMoto(motorcycle)).thenReturn(expectedMvm);

        this.mockMvc.perform(post("/moto")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void createMotorcycleMissingRequiredShouldReturn422() throws Exception {
        Motorcycle inputMotorcycle = new Motorcycle();

        String inputJson = mapper.writeValueAsString(inputMotorcycle);

        this.mockMvc.perform(post("/moto")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void getAllMotorcycles() throws Exception {
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

        List<Motorcycle> productList = new ArrayList<>();
        productList.add(motorcycle);
        productList.add(motorcycle1);

        //Object to JSON in String
        when(serviceLayer.getAllMoto()).thenReturn(productList);

        List<Motorcycle> listChecker = new ArrayList<>();
        listChecker.addAll(productList);

        String outputJson = mapper.writeValueAsString(listChecker);

        this.mockMvc.perform(get("/moto")
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }
}