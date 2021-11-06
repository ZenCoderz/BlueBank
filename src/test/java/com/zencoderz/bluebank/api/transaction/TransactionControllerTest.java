package com.zencoderz.bluebank.api.transaction;

import com.google.gson.Gson;
import com.zencoderz.bluebank.BluebankApplicationTests;
import com.zencoderz.bluebank.api.account.Account;
import com.zencoderz.bluebank.api.account.AccountService;
import com.zencoderz.bluebank.api.account.dto.AccountDTO;
import com.zencoderz.bluebank.api.transaction.dto.TransactionDTO;
import com.zencoderz.bluebank.api.transaction.dto.TransactionFormCreateDTO;
import com.zencoderz.bluebank.exception.BlueBankRunTimeExceptionHandler;
import com.zencoderz.bluebank.factory.TransactionTestUtil;

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

public class TransactionControllerTest extends BluebankApplicationTests {

    private MockMvc mockMvc;

    private final Gson gson = new Gson();

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionController transactionController;

    @Autowired
    private TransactionTestUtil util;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(transactionController)
                .setControllerAdvice(new BlueBankRunTimeExceptionHandler()).build();
    }

    @Test
    public void createTransaction_WithSufficientBalanceToTransfer() throws Exception {
        Transaction transaction = this.util.buildTransaction();
        transaction.setAmount(transaction.getFrom().getBalance() - 5000);

        TransactionFormCreateDTO transactionFormCreateDTO = this.util.convertTransactionToForm(transaction);

        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post("/transactions")
                .contentType(MediaType.APPLICATION_JSON).content(this.gson.toJson(transactionFormCreateDTO)))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assertions.assertNotNull(response, "Response Shouldn't be Null");
        Assertions.assertEquals(HttpStatus.CREATED.value(), response.getStatus());

        TransactionDTO transactionDTO = this.gson.fromJson(response.getContentAsString(), TransactionDTO.class);

        Assertions.assertEquals(transaction.getFrom().getId(), transactionDTO.getFrom().getId());
        Assertions.assertEquals(transaction.getTo().getId(), transactionDTO.getTo().getId());
        Assertions.assertEquals(transaction.getAmount(), transactionDTO.getAmount());
        this.checkIfTransactionWasPersisted(transactionDTO);
        this.checkIfAccountsGotModified(transaction.getFrom(), transaction.getTo(), transactionDTO);
    }

    @Test
    public void createTransaction_WithoutSufficientBalanceToTransfer() throws Exception {
        Transaction transaction = this.util.buildTransaction();
        transaction.setAmount(transaction.getFrom().getBalance() + 5000);

        TransactionFormCreateDTO transactionFormCreateDTO = this.util.convertTransactionToForm(transaction);

        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post("/transactions")
                .contentType(MediaType.APPLICATION_JSON).content(this.gson.toJson(transactionFormCreateDTO)))
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        Assertions.assertNotNull(response);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    public void createTransaction_WithNull() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/transactions")
                .contentType(MediaType.APPLICATION_JSON).content(""))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    private void checkIfTransactionWasPersisted(TransactionDTO transactionDTO) {
        TransactionDTO fetchedTransaction = this.transactionService.findTransactionDTOById(transactionDTO.getId());
        Assertions.assertEquals(transactionDTO.getAmount(), fetchedTransaction.getAmount());
        Assertions.assertEquals(transactionDTO.getFrom(), fetchedTransaction.getFrom());
        Assertions.assertEquals(transactionDTO.getTo(), fetchedTransaction.getTo());
    }

    private void checkIfAccountsGotModified(Account from, Account to, TransactionDTO transactionDTO) {
        AccountDTO fetchedFrom = this.accountService.findAccountDTOById(from.getId());
        AccountDTO fetchedTo = this.accountService.findAccountDTOById(to.getId());
        Assertions.assertEquals(fetchedFrom.getBalance(),  from.getBalance() - transactionDTO.getAmount());
        Assertions.assertEquals(fetchedTo.getBalance(), to.getBalance() + transactionDTO.getAmount());
    }

}