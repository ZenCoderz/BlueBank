package com.zencoderz.bluebank.api.transaction.dto;

import javax.validation.constraints.DecimalMin;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

//@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionFormCreateDTO {

	@DecimalMin("0.01")
	private Double ammount;
	
	public Double getAmmount() {
		return this.ammount;
	}
	
	public void setAmmount(Double ammount) {
		this.ammount = ammount;
	}
	
}
