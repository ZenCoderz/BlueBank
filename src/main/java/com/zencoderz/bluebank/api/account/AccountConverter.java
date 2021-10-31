package com.zencoderz.bluebank.api.account;

import com.zencoderz.bluebank.api.account.dto.AccountDTO;
import com.zencoderz.bluebank.api.account.dto.AccountFormCreateDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AccountConverter {

    public Set<AccountDTO> convertAccountsToDTO(List<Account>accounts) {
        return accounts.stream().map(this::convertAccountToDTO).collect(Collectors.toSet());
    }

    public AccountDTO convertAccountToDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setAgency(account.getAgency());
        accountDTO.setAccountNumber(account.getAccountNumber());
        accountDTO.setDigit(account.getDigit());
        accountDTO.setBalance(account.getBalance());
        accountDTO.setCredit(account.getCredit());
        return accountDTO;
    }

    public Account convertCreateFormToAccount(AccountFormCreateDTO accountFormCreateDTO) {
        Account account = new Account();
        account.setAgency(accountFormCreateDTO.getAgency());
        account.setAccountNumber(accountFormCreateDTO.getAccountNumber());
        account.setBalance(20000D);
        account.setDigit(accountFormCreateDTO.getDigit());
        account.setCredit(accountFormCreateDTO.getCredit());
        return account;
    }

}
