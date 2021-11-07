package com.zencoderz.bluebank.api.transaction;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.zencoderz.bluebank.api.transaction.dto.TransactionDTO;
import com.zencoderz.bluebank.api.transaction.dto.TransactionFormCreateDTO;
import com.zencoderz.bluebank.api.transaction.dto.TransactionFormUpdateDTO;

public interface TransactionService {
	
	TransactionDTO createTransaction(TransactionFormCreateDTO transactionFormCreateDTO);
	List<TransactionDTO> getTransactionsDTO();
	TransactionDTO findTransactionDTOById(UUID id);
	TransactionDTO updateTransaction(UUID id, TransactionFormUpdateDTO transactionFormUpdateDTO);
	void deleteTransaction (UUID id);
	
}
