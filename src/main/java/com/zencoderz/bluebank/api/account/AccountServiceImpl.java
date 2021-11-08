package com.zencoderz.bluebank.api.account;

import com.zencoderz.bluebank.api.account.dto.AccountDTO;
import com.zencoderz.bluebank.api.account.dto.AccountFormCreateDTO;
import com.zencoderz.bluebank.api.account.dto.AccountFormUpdateDTO;
import com.zencoderz.bluebank.api.user.User;
import com.zencoderz.bluebank.api.user.UserService;
import com.zencoderz.bluebank.exception.ContentNotFoundException;
import com.zencoderz.bluebank.exception.InvalidInputException;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    private AccountConverter accountConverter;
    private UserService userService;

    @Override
    public Account findAccountById(Long id) {
        Optional<Account> accountOptional = this.accountRepository.findById(id);
        if (accountOptional.isEmpty()) {
            throw new ContentNotFoundException("Account not found");
        }
        return accountOptional.get();
    }

    @Override
    public Set<AccountDTO> getAccountsDTO() {
        List<Account> accounts = this.accountRepository.findAll();
        return this.accountConverter.convertAccountsToDTO(accounts);
    }

    @Override
    public AccountDTO findAccountDTOById(Long id) {
        Account account = this.findAccountById(id);
        return this.accountConverter.convertAccountToDTO(account);
    }

    @Override
    public AccountDTO createAccount(Long userId, AccountFormCreateDTO accountFormCreateDTO) {
        Account account = this.accountConverter.convertCreateFormToAccount(accountFormCreateDTO);
        User user = this.userService.findUserById(userId);
        if (user.getAccount() != null) {
            throw new InvalidInputException("User " + user.getName() + " already has a Account");
        }
        account.setUser(user);
        this.accountRepository.save(account);
        return this.accountConverter.convertAccountToDTO(account);
    }

    @Override
    public AccountDTO updateAccount(Long id, AccountFormUpdateDTO accountFormUpdateDTO) {
        Account account = this.findAccountById(id);
        account.setAccountNumber(accountFormUpdateDTO.getAccountNumber());
        account.setAgency(accountFormUpdateDTO.getAgency());
        account.setDigit(accountFormUpdateDTO.getDigit());
        account.setCredit(accountFormUpdateDTO.getCredit());
        this.accountRepository.save(account);
        return this.accountConverter.convertAccountToDTO(account);
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = this.findAccountById(id);
        this.accountRepository.delete(account);
    }

}
