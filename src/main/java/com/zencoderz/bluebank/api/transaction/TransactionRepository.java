package com.zencoderz.bluebank.api.transaction;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByOrderByCreatedAt();

    @Query(value = "SELECT transaction From Transaction transaction WHERE transaction.from.id = :accountId ", nativeQuery = false)
    List<Transaction> findAllByFromAndOrderByCreatedAt(Long accountId);

}
	
	

