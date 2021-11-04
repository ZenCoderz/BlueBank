package com.zencoderz.bluebank.api.transaction;

import org.springframework.stereotype.Component;

import com.zencoderz.bluebank.api.transaction.dto.TransactionDTO;
import com.zencoderz.bluebank.api.transaction.dto.TransactionFormCreateDTO;

@Component
public class TransactionConverter {

	public TransactionDTO convertTransactionToDTO (Transaction transaction) {
		TransactionDTO transactionDTO = new TransactionDTO();
		transactionDTO.setId(transaction.getId());
		transactionDTO.setDate(transaction.getDate());
		transactionDTO.setAmount(transaction.getAmount());
		return transactionDTO;
	}
	
	public Transaction convertCreateFormToTransaction(TransactionFormCreateDTO transactionFormCreateDTO) {
		Transaction transaction = new Transaction();
		transaction.setAmount(transactionFormCreateDTO.getAmount());
		return transaction;
	}
	
}
