package com.zencoderz.bluebank.api.transaction.dto;

import com.zencoderz.bluebank.api.account.dto.AccountDTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

	private Long id;
	
	private String createdAt;

	private Double amount;

	private AccountDTO from;

	private AccountDTO to;

}
