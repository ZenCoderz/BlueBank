package com.zencoderz.bluebank.api.transaction;

import java.util.UUID;

import com.zencoderz.bluebank.api.transaction.dto.TransactionDTO;
import com.zencoderz.bluebank.api.transaction.dto.TransactionFormCreateDTO;

public interface TransactionService {
	
	TransactionDTO createTransaction(TransactionFormCreateDTO transactionFormCreateDTO);
	TransactionDTO getTransactionDTOById(UUID id);
	void deleteTransaction (UUID id);
	
}
