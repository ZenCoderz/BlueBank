package com.zencoderz.bluebank.api.transaction.dto;

import javax.validation.constraints.DecimalMin;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionFormCreateDTO {

	@DecimalMin("0.01")
	private Double ammount;
	
}
