package com.zencoderz.bluebank.api.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
