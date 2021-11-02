package com.zencoderz.bluebank.api.transaction.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

//@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

	private UUID id;
	
	private LocalDateTime date;
	
	private Double ammount;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Double getAmmount() {
		return ammount;
	}

	public void setAmmount(Double ammount) {
		this.ammount = ammount;
	}
	
}
