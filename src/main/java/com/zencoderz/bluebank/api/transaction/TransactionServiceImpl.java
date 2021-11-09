package com.zencoderz.bluebank.api.transaction;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.zencoderz.bluebank.api.account.Account;
import com.zencoderz.bluebank.api.account.AccountService;
import com.zencoderz.bluebank.api.transaction.dto.TransactionFormUpdateDTO;
import com.zencoderz.bluebank.exception.InvalidInputException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.zencoderz.bluebank.api.transaction.dto.TransactionDTO;
import com.zencoderz.bluebank.api.transaction.dto.TransactionFormCreateDTO;

import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class TransactionServiceImpl implements TransactionService {

	private TransactionRepository transactionRepository;
	private TransactionConverter transactionConverter;
	private AccountService accountService;
	private final EntityManager entityManager;

	private Transaction findTransactionById(Long id) {
        Optional<Transaction> transactionOptional = this.transactionRepository.findById(id);
        if (transactionOptional.isEmpty()) {
            throw new RuntimeException("Transaction not found");
        }
        return transactionOptional.get();
    }

	@Override
	public List<TransactionDTO> getTransactionsByAccountId(Long accountId) {
		return this.transactionConverter.convertTransactionsToDTO(
				this.transactionRepository.findAllByFromAndOrderByCreatedAt(accountId));
	}

	@Override
	public TransactionDTO createTransaction(TransactionFormCreateDTO transactionFormCreateDTO) {
		Account from = this.accountService.findAccountById(transactionFormCreateDTO.getFromAccountId());
		if (from.getId().equals(transactionFormCreateDTO.getToAccountId())) {
			throw new InvalidInputException("You can't transfer to your own account");
		}
		Account to = this.accountService.findAccountById(transactionFormCreateDTO.getToAccountId());
		Transaction transaction = this.makeTransaction(from, to, transactionFormCreateDTO.getAmount());
        return this.transactionConverter.convertTransactionToDTO(transaction);
	}

	@Override
	public List<TransactionDTO> getTransactionsDTO() {
		List<Transaction> transactions = this.transactionRepository.findAllByOrderByCreatedAt();
		return this.transactionConverter.convertTransactionsToDTO(transactions);
	}

	@Override
	public TransactionDTO findTransactionDTOById(Long id) {
		Transaction transaction = this.findTransactionById(id);
        return this.transactionConverter.convertTransactionToDTO(transaction);
	}

	@Override
	public TransactionDTO updateTransaction(Long id, TransactionFormUpdateDTO transactionFormUpdateDTO) {
		Transaction transaction = this.findTransactionById(id);
		transaction.setAmount(transactionFormUpdateDTO.getAmount());
		this.transactionRepository.save(transaction);
		return this.transactionConverter.convertTransactionToDTO(transaction);
	}

	@Override
	public void deleteTransaction(Long id) {
		Transaction transaction = this.findTransactionById(id);
		this.transactionRepository.delete(transaction);
	}

	private Transaction makeTransaction(Account from, Account to, Double amount) {
		this.transferAmountFromTo(from, to, amount);
		Transaction transaction = new Transaction();
		transaction.setFrom(from);
		transaction.setTo(to);
		transaction.setAmount(amount);
		this.transactionRepository.save(transaction);
		this.entityManager.flush();
		this.entityManager.refresh(transaction);
		return transaction;
	}

	private void transferAmountFromTo(Account from, Account to, Double value) {
		if (from.getBalance() < value) {
			throw new InvalidInputException("You can't make a transference bigger than your balance");
		}
		from.setBalance(from.getBalance() - value);
		to.setBalance(to.getBalance() + value);
	}

}
