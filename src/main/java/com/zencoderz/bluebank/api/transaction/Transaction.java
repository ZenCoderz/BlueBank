package com.zencoderz.bluebank.api.transaction;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.*;

import com.zencoderz.bluebank.api.account.Account;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

	@Column(updatable = false)
	@CreationTimestamp
    private LocalDateTime createdAt;
    
    private Double amount;

	@ManyToOne(fetch = FetchType.LAZY)
	private Account from;

	@ManyToOne(fetch = FetchType.LAZY)
	private Account to;

	public Transaction () {
    	
    }

    public Transaction (UUID id, LocalDateTime createdAt, Double amount) {
    	this.id = id;
    	this.createdAt = createdAt;
    	this.amount = amount;
    }

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Account getFrom() {
		return from;
	}

	public void setFrom(Account from) {
		this.from = from;
	}

	public Account getTo() {
		return to;
	}

	public void setTo(Account to) {
		this.to = to;
	}

}