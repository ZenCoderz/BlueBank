package com.zencoderz.bluebank.api.account.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

    private Long id;
    
    private String agency;

    private String accountNumber;

	private String digit;

    private Double balance;

    private Double credit ;

}
