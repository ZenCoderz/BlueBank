package com.zencoderz.bluebank.api.transaction;

import java.util.Set;
import java.util.UUID;

import com.zencoderz.bluebank.api.transaction.dto.TransactionDTO;
import com.zencoderz.bluebank.api.transaction.dto.TransactionFormCreateDTO;

public interface TransactionService {
	
	TransactionDTO createTransaction(TransactionFormCreateDTO transactionFormCreateDTO);
	Set<TransactionDTO> getTransactionsDTO();
	TransactionDTO findTransactionDTOById(UUID id);
	void deleteTransaction (UUID id);
	
}
