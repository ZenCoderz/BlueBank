package com.zencoderz.bluebank.api.transaction;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import com.zencoderz.bluebank.api.account.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zencoderz.bluebank.api.transaction.dto.TransactionDTO;
import com.zencoderz.bluebank.api.transaction.dto.TransactionFormCreateDTO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private TransactionConverter transactionConverter;
	
	private Transaction findTransactionById(UUID id) {
        Optional<Transaction> transactionOptional = this.transactionRepository.findById(id);
        if (transactionOptional.isEmpty()) {
            throw new RuntimeException("Transaction not found");
        }
        return transactionOptional.get();
    }
	
	@Override
	public TransactionDTO createTransaction(TransactionFormCreateDTO transactionFormCreateDTO) {
		Transaction transaction = 
				this.transactionConverter.convertCreateFormToTransaction(transactionFormCreateDTO);
        this.transactionRepository.save(transaction);
        return this.transactionConverter.convertTransactionToDTO(transaction);
	}

	@Override
	public Set<TransactionDTO> getTransactionsDTO() {
		List<Transaction> transactions = this.transactionRepository.findAll();
		return this.transactionConverter.convertTransactionsToDTO(transactions);
	}

	@Override
	public TransactionDTO getTransactionDTOById(UUID id) {
		Transaction transaction = this.findTransactionById(id);
        return this.transactionConverter.convertTransactionToDTO(transaction);
	}

	@Override
	public void deleteTransaction(UUID id) {
		Transaction transaction = this.findTransactionById(id);
		this.transactionRepository.delete(transaction);
	}

}
