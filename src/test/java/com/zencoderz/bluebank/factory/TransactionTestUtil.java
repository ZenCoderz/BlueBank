package com.zencoderz.bluebank.factory;

import com.zencoderz.bluebank.api.account.Account;
import com.zencoderz.bluebank.api.account.AccountRepository;
import com.zencoderz.bluebank.api.transaction.Transaction;
import com.zencoderz.bluebank.api.transaction.dto.TransactionFormCreateDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class TransactionTestUtil extends TestUtilWithUniqueIdentifier {

    private AccountTestUtil accountFactoryUtil;
    private AccountRepository accountRepository;

    public Transaction buildTransaction() {
        Account from = this.accountFactoryUtil.buildAccount();
        Account to = this.accountFactoryUtil.buildAccount();
        this.accountRepository.save(from);
        this.accountRepository.save(to);
        Transaction transaction = new Transaction();
        transaction.setAmount(300D);
        transaction.setFrom(from);
        transaction.setTo(to);
        transaction.setCreatedAt(LocalDateTime.now());
        return transaction;
    }

    public TransactionFormCreateDTO convertTransactionToForm(Transaction transaction) {
        TransactionFormCreateDTO transactionFormCreateDTO = new TransactionFormCreateDTO();
        transactionFormCreateDTO.setAmount(transaction.getAmount());
        transactionFormCreateDTO.setFrom(transaction.getFrom().getId());
        transactionFormCreateDTO.setTo(transaction.getTo().getId());
        return transactionFormCreateDTO;
    }

}
