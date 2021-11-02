package com.zencoderz.bluebank.api.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

//@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountFormUpdateDTO {

    public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getDigit() {
		return digit;
	}

	public void setDigit(String digit) {
		this.digit = digit;
	}

	public Double getCredit() {
		return credit;
	}

	public void setCredit(Double credit) {
		this.credit = credit;
	}

	@Size(min=4, max=4)
    private String agency;

    @Size(min=1, max=10)
    private String accountNumber;

    @Size(min=1, max=1)
    private String digit;

    private Double credit = 0D;

}
