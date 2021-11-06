package com.zencoderz.bluebank.factory;

import com.zencoderz.bluebank.api.account.Account;
import com.zencoderz.bluebank.api.account.AccountRepository;
import com.zencoderz.bluebank.api.transaction.Transaction;
import com.zencoderz.bluebank.api.user.User;
import com.zencoderz.bluebank.api.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class AccountTestUtil extends TestUtilWithUniqueIdentifier {

    @Autowired
    private UserTestUtil userTestUtil;

    @Autowired
    private UserRepository userRepository;

    public Account buildAccount() {
        User user = this.userTestUtil.buildUser();
        this.userRepository.save(user);
        Account account = new Account();
        account.setDigit("1");
        account.setAccountNumber("1111" + this.getUniqueIdentifier());
        account.setAgency("1111");
        account.setCredit(10000D);
        account.setBalance(25000D);
        account.setUser(user);
        return account;
    }

}
