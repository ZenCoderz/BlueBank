package com.zencoderz.bluebank.api.transaction;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zencoderz.bluebank.BluebankApplicationTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
public class TransactionControllerTest extends BluebankApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private TransactionController transactionController;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();
    }

    @Test
    public void createTransactionWithCorrectData_ReturnStatusCode201() throws Exception {
        Transaction transaction = new Transaction(null, null, 2.500);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(transaction);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/transactions")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void createTransactionWithIncorrectData_ReturnStatusCode400() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/transactions")
                .contentType(MediaType.APPLICATION_JSON).content(""))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}