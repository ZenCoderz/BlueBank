package com.zencoderz.bluebank.api.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zencoderz.bluebank.BluebankApplicationTests;
import com.zencoderz.bluebank.api.account.dto.AccountFormCreateDTO;

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
public class AccountControllerTest extends BluebankApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private AccountController accountController;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }

    @Test
    public void createAccountWithCorrectData_ReturnStatusCode201() throws Exception {
        AccountFormCreateDTO account = new AccountFormCreateDTO("0123","123456789", "0",250.00);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(account);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/accounts")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void createAccountWithIncorrectData_ReturnStatusCode400() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/accounts")
                .contentType(MediaType.APPLICATION_JSON).content(""))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}
