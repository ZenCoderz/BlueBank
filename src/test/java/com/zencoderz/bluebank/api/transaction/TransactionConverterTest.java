package com.zencoderz.bluebank.api.transaction;

import com.zencoderz.bluebank.api.transaction.dto.TransactionDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.zencoderz.bluebank.api.transaction.dto.TransactionFormCreateDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class TransactionConverterTest {

    TransactionConverter transactionConverter;

    @BeforeEach
    public void setUp() {
        transactionConverter = new TransactionConverter();
    }

    @Test
    @DisplayName("Conversion from Transaction to DTO returns expected object")
    public void convertTransactionToTransactionDTOReturnsExpectedObject() {
        Transaction transaction = new Transaction(UUID.randomUUID(), LocalDateTime.now(), 500d);
        TransactionDTO transactionDTO = transactionConverter.convertTransactionToDTO(transaction);
        TransactionDTO expectedTransactionDTO = new TransactionDTO(
                transaction.getId(),
                transaction.getDate(),
                transaction.getAmount()
        );
        assertEquals(expectedTransactionDTO, transactionDTO);
    }

    @Test
    @DisplayName("Conversion from TransactionFormCreateDTO to Transaction returns expected object")
    public void convertTransactionFormCreateDTOToTransactionReturnsExpectedObject() {
        TransactionFormCreateDTO transactionFormCreateDTO = new TransactionFormCreateDTO(500d);
        Transaction transaction = transactionConverter.convertCreateFormToTransaction(transactionFormCreateDTO);
        Transaction expectedTransaction = new Transaction();
        expectedTransaction.setAmount(transactionFormCreateDTO.getAmount());
        assertEquals(expectedTransaction, transaction);
    }

}
