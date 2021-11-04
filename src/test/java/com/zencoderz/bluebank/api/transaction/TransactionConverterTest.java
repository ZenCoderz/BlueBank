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
    public void convertTransactionToTransactionDTO_ReturnsExpectedObject() {
        Transaction transaction = new Transaction(UUID.randomUUID(), LocalDateTime.now(), 500d);
        TransactionDTO transactionDTO = transactionConverter.convertTransactionToDTO(transaction);

        assertEquals(transaction.getId(), transactionDTO.getId());
        assertEquals(transaction.getDate(), transactionDTO.getDate());
        assertEquals(transaction.getAmount(), transactionDTO.getAmount());
    }

    @Test
    @DisplayName("Conversion from Transaction to DTO does not return default values")
    public void convertTransactionToTransactionDTO_DoesNotReturnsDefaultValues() {
        Transaction transaction = new Transaction(null, null, null);
        TransactionDTO transactionDTO = transactionConverter.convertTransactionToDTO(transaction);

        assertEquals(null, transactionDTO.getId());
        assertEquals(null, transactionDTO.getDate());
        assertEquals(null, transactionDTO.getAmount());
    }

    @Test
    @DisplayName("Conversion from TransactionFormCreateDTO to Transaction returns expected object")
    public void convertTransactionFormCreateDTOToTransaction_ReturnsExpectedObject() {
        TransactionFormCreateDTO transactionFormCreateDTO = new TransactionFormCreateDTO(500d);
        Transaction transaction = transactionConverter.convertCreateFormToTransaction(transactionFormCreateDTO);

        assertEquals(transactionFormCreateDTO.getAmount(), transaction.getAmount());
    }

}
