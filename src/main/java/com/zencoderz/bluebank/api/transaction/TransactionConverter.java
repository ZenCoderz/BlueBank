package com.zencoderz.bluebank.api.transaction;

import org.springframework.stereotype.Component;

import com.zencoderz.bluebank.api.transaction.dto.TransactionDTO;
import com.zencoderz.bluebank.api.transaction.dto.TransactionFormCreateDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TransactionConverter {

	public TransactionDTO convertTransactionToDTO (Transaction transaction) {
		TransactionDTO transactionDTO = new TransactionDTO();
		transactionDTO.setId(transaction.getId());
		transactionDTO.setCreatedAt(transaction.getCreatedAt());
		transactionDTO.setAmount(transaction.getAmount());
		return transactionDTO;
	}

	public Set<TransactionDTO> convertTransactionsToDTO (List<Transaction> transactions) {
		return transactions.stream().map(this::convertTransactionToDTO).collect(Collectors.toSet());
	}

	public Transaction convertCreateFormToTransaction(TransactionFormCreateDTO transactionFormCreateDTO) {
		Transaction transaction = new Transaction();
		transaction.setAmount(transactionFormCreateDTO.getAmount());
		return transaction;
	}

}
