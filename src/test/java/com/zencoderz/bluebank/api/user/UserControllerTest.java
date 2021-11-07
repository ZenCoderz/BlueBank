package com.zencoderz.bluebank.api.user;

import com.google.gson.Gson;
import com.zencoderz.bluebank.BluebankApplicationTests;
import com.zencoderz.bluebank.api.user.dto.UserDTO;
import com.zencoderz.bluebank.api.user.dto.UserFormCreateDTO;
import com.zencoderz.bluebank.factory.UserTestUtil;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class UserControllerTest extends BluebankApplicationTests {

    private MockMvc mockMvc;

    private final Gson gson = new Gson();

    @Autowired
    private UserController userController;

    @Autowired
    private UserTestUtil util;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void register_WithCorrectData() throws Exception {
        UserFormCreateDTO user = this.util.buildUserFormCreateDTO();
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post("/users/register")
                .contentType(MediaType.APPLICATION_JSON).content(gson.toJson(user)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").doesNotExist())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();

        Assertions.assertNotNull(response, "Response Shouldn't be Null");
        Assertions.assertEquals(HttpStatus.CREATED.value(), response.getStatus());

        UserDTO userDTO = this.gson.fromJson(response.getContentAsString(), UserDTO.class);

        Assertions.assertNotNull(userDTO.getId());
        Assertions.assertEquals(user.getName(), userDTO.getName());
        Assertions.assertEquals(user.getUsername(), userDTO.getUsername());
        Assertions.assertEquals(user.getIdentifier(), userDTO.getIdentifier());
        Assertions.assertEquals(user.getIdentifierType(), userDTO.getIdentifierType());
    }

    @Test
    public void register_WithEmptyData() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/users/register")
                .contentType(MediaType.APPLICATION_JSON).content(""))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void register_WithEmptyUsername() throws Exception {
        UserFormCreateDTO user = this.util.buildUserFormCreateDTO();
        user.setUsername("");
        this.mockMvc.perform(MockMvcRequestBuilders.post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON).content(gson.toJson(user)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void register_WithEmptyIdentifier() throws Exception {
        UserFormCreateDTO user = this.util.buildUserFormCreateDTO();
        user.setIdentifier("");
        this.mockMvc.perform(MockMvcRequestBuilders.post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON).content(gson.toJson(user)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void register_WithEmptyIdentifierType() throws Exception {
        UserFormCreateDTO user = this.util.buildUserFormCreateDTO();
        user.setIdentifierType(null);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON).content(gson.toJson(user)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}