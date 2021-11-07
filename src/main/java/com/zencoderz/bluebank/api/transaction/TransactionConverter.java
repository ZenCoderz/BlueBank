package com.zencoderz.bluebank.api.transaction;

import lombok.AllArgsConstructor;

import org.apache.commons.text.WordUtils;
import org.springframework.stereotype.Component;

import com.zencoderz.bluebank.api.transaction.dto.TransactionDTO;
import com.zencoderz.bluebank.api.account.AccountConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class TransactionConverter {

	private AccountConverter accountConverter;

	public TransactionDTO convertTransactionToDTO (Transaction transaction) {
		TransactionDTO transactionDTO = new TransactionDTO();
		if (transaction == null) {
			return transactionDTO;
		}
		transactionDTO.setId(transaction.getId());
		transactionDTO.setCreatedAt(this.formatDate(transaction.getCreatedAt()));
		transactionDTO.setAmount(transaction.getAmount());
		if (transaction.getFrom() != null && transaction.getTo() != null) {
			transactionDTO.setFrom(this.accountConverter.convertAccountToDTO(transaction.getFrom()));
			transactionDTO.setTo(this.accountConverter.convertAccountToDTO(transaction.getTo()));
		}
		return transactionDTO;
	}

	public List<TransactionDTO> convertTransactionsToDTO (List<Transaction> transactions) {
		if (transactions == null) {
			return new ArrayList<>();
		}
		ArrayList<TransactionDTO> transactionsDTOReturn = new ArrayList<>();
		for (Transaction t : transactions) {
			transactionsDTOReturn.add(this.convertTransactionToDTO(t));
		}
		return transactionsDTOReturn;
	}

	private String formatDate(LocalDateTime localDateTime) {
		if (localDateTime == null) {
			return null;
		}
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd yy HH:mm");
		String date = localDateTime.format(dateFormat);
		return WordUtils.capitalize(date);
	}

}
