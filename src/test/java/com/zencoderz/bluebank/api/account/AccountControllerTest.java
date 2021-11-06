package com.zencoderz.bluebank.api.account;

import com.google.gson.Gson;
import com.zencoderz.bluebank.BluebankApplicationTests;
import com.zencoderz.bluebank.api.account.dto.AccountDTO;
import com.zencoderz.bluebank.api.account.dto.AccountFormCreateDTO;
import com.zencoderz.bluebank.api.user.User;
import com.zencoderz.bluebank.exception.BlueBankRunTimeExceptionHandler;
import com.zencoderz.bluebank.factory.AccountTestUtil;

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

public class AccountControllerTest extends BluebankApplicationTests {

    private MockMvc mockMvc;

    private final Gson gson = new Gson();

    @Autowired
    private AccountController accountController;

    @Autowired
    private AccountTestUtil accountTestUtil;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(accountController)
                .setControllerAdvice(new BlueBankRunTimeExceptionHandler()).build();;
    }

    @Test
    public void createAccount_WithCorrectData() throws Exception {
        Account account = this.accountTestUtil.buildAccount();

        AccountFormCreateDTO accountFormCreateDTO = new AccountFormCreateDTO();
        accountFormCreateDTO.setAccountNumber(account.getAccountNumber());
        accountFormCreateDTO.setCredit(account.getCredit());
        accountFormCreateDTO.setAgency(account.getAgency());
        accountFormCreateDTO.setDigit(account.getDigit());

        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders
                .post("/accounts/user/" + account.getUser().getId())
                .contentType(MediaType.APPLICATION_JSON).content(this.gson.toJson(accountFormCreateDTO)))
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.CREATED.value(), response.getStatus());

        AccountDTO accountDTO = this.gson.fromJson(response.getContentAsString(), AccountDTO.class);

        Assertions.assertEquals(account.getAccountNumber(), accountDTO.getAccountNumber());
        Assertions.assertEquals(account.getAgency(), accountDTO.getAgency());
        Assertions.assertEquals(account.getDigit(), accountDTO.getDigit());
        Assertions.assertEquals(20000D, accountDTO.getBalance());
        Assertions.assertEquals(account.getCredit(), accountDTO.getCredit());
    }

    @Test
    public void createAccount_WithNull() throws Exception {
        User user = this.accountTestUtil.buildAccount().getUser();
        this.mockMvc.perform(MockMvcRequestBuilders.post("/accounts/user/" + user.getId())
                .contentType(MediaType.APPLICATION_JSON).content(""))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}
