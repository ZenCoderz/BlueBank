package com.zencoderz.bluebank.api.transaction.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

	private UUID id;
	
	private LocalDateTime date;
	
	private Double amount;

}
