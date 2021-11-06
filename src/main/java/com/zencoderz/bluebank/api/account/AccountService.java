package com.zencoderz.bluebank.api.account;

import com.zencoderz.bluebank.api.account.dto.AccountDTO;
import com.zencoderz.bluebank.api.account.dto.AccountFormCreateDTO;
import com.zencoderz.bluebank.api.account.dto.AccountFormUpdateDTO;

import java.util.Set;
import java.util.UUID;

public interface AccountService {

    Set<AccountDTO> getAccountsDTO();
    AccountDTO getAccountDTOById(UUID id);
    AccountDTO createAccount(UUID userId, AccountFormCreateDTO accountFormCreateDTO);
    AccountDTO updateAccount(UUID id, AccountFormUpdateDTO accountFormUpdateDTO);
    void deleteAccount(UUID id);

}
