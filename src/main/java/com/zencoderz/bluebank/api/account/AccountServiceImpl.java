package com.zencoderz.bluebank.api.account;

import com.zencoderz.bluebank.api.account.dto.AccountDTO;
import com.zencoderz.bluebank.api.account.dto.AccountFormCreateDTO;
import com.zencoderz.bluebank.api.account.dto.AccountFormUpdateDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    private AccountConverter accountConverter;

    private Account findAccountById(UUID id) {
        Optional<Account> accountOptional = this.accountRepository.findById(id);
        if (accountOptional.isEmpty()) {
            throw new RuntimeException("Account not found");
        }
        return accountOptional.get();
    }

    @Override
    public Set<AccountDTO> getAccountsDTO() {
        List<Account> accounts = this.accountRepository.findAll();
        return this.accountConverter.convertAccountsToDTO(accounts);
    }

    @Override
    public AccountDTO getAccountDTOById(UUID id) {
        Account account = this.findAccountById(id);
        return this.accountConverter.convertAccountToDTO(account);
    }

    @Override
    public AccountDTO createAccount(AccountFormCreateDTO accountFormCreateDTO) {
        Account account = this.accountConverter.convertCreateFormToAccount(accountFormCreateDTO);
        this.accountRepository.save(account);
        return this.accountConverter.convertAccountToDTO(account);
    }

    @Override
    public AccountDTO updateAccount(UUID id, AccountFormUpdateDTO accountFormUpdateDTO) {
        Account account = this.findAccountById(id);
        account.setAccountNumber(accountFormUpdateDTO.getAccountNumber());
        account.setAgency(accountFormUpdateDTO.getAgency());
        account.setDigit(accountFormUpdateDTO.getDigit());
        account.setCredit(accountFormUpdateDTO.getCredit());
        this.accountRepository.save(account);
        return this.accountConverter.convertAccountToDTO(account);
    }

    @Override
    public void deleteAccount(UUID id) {
        Account account = this.findAccountById(id);
        this.accountRepository.delete(account);
    }

}
