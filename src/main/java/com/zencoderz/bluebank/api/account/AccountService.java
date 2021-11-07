package com.zencoderz.bluebank.api.account;

import com.zencoderz.bluebank.api.account.dto.AccountDTO;
import com.zencoderz.bluebank.api.account.dto.AccountFormCreateDTO;
import com.zencoderz.bluebank.api.account.dto.AccountFormUpdateDTO;

import java.util.Set;


public interface AccountService {

    Account findAccountById(Long id);
    Set<AccountDTO> getAccountsDTO();
    AccountDTO findAccountDTOById(Long id);
    AccountDTO createAccount(Long userId, AccountFormCreateDTO accountFormCreateDTO);
    AccountDTO updateAccount(Long id, AccountFormUpdateDTO accountFormUpdateDTO);
    void deleteAccount(Long id);

}
