package com.zencoderz.bluebank.api.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.zencoderz.bluebank.BluebankApplicationTests;
import com.zencoderz.bluebank.api.account.AccountConverter;
import com.zencoderz.bluebank.api.transaction.dto.TransactionDTO;
import com.zencoderz.bluebank.factory.TransactionTestUtil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TransactionConverterTest extends BluebankApplicationTests {

    @Autowired
    private TransactionConverter transactionConverter;

    @Autowired
    private TransactionTestUtil transactionTestUtil;

    @BeforeEach
    public void setUp() {
        transactionConverter = new TransactionConverter(new AccountConverter());
    }

    @Test
    public void convertTransactionToTransactionDTO_ReturnsExpectedObject() {
        Transaction transaction = this.transactionTestUtil.buildTransaction();
        TransactionDTO transactionDTO = transactionConverter.convertTransactionToDTO(transaction);
        assertEquals(transaction.getId(), transactionDTO.getId());
        assertEquals(transaction.getAmount(), transactionDTO.getAmount());
        assertEquals(transaction.getFrom().getId(), transactionDTO.getFrom().getId());
        assertEquals(transaction.getTo().getId(), transactionDTO.getTo().getId());
    }

    @Test
    public void convertTransactionToTransactionDTO_DoesReturnsDefaultValues() {
        Transaction transaction = new Transaction();
        TransactionDTO transactionDTO = this.transactionConverter.convertTransactionToDTO(transaction);
        assertNull(transactionDTO.getId());
        assertNull(transactionDTO.getCreatedAt());
        assertNull(transactionDTO.getAmount());
    }

}
