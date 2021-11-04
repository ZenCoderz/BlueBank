package com.zencoderz.bluebank.api.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zencoderz.bluebank.BluebankApplicationTests;
import com.zencoderz.bluebank.auth.user.UserController;
import com.zencoderz.bluebank.auth.user.attributes.IdentifierType;
import com.zencoderz.bluebank.auth.user.dto.UserFormCreateDTO;

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
public class UserControllerTest extends BluebankApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private UserController userController;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void createUserWithCorrectData_ReturnStatusCode201() throws Exception {
        UserFormCreateDTO user = new UserFormCreateDTO("Paul", "paul@email.com","123456","276.577.240-13", IdentifierType.CPF);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(user);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void createUserWithIncorrectData_ReturnStatusCode400() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON).content(""))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}