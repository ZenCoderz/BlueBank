package com.zencoderz.bluebank.api.transaction;

import java.util.List;

import com.zencoderz.bluebank.api.transaction.dto.TransactionDTO;
import com.zencoderz.bluebank.api.transaction.dto.TransactionFormCreateDTO;
import com.zencoderz.bluebank.api.transaction.dto.TransactionFormUpdateDTO;

public interface TransactionService {
	
	TransactionDTO createTransaction(TransactionFormCreateDTO transactionFormCreateDTO);
	List<TransactionDTO> getTransactionsDTO();
	TransactionDTO findTransactionDTOById(Long id);
	List<TransactionDTO> getTransactionsByAccountId(Long accountId);
	TransactionDTO updateTransaction(Long id, TransactionFormUpdateDTO transactionFormUpdateDTO);
	void deleteTransaction (Long id);

}
