package com.zencoderz.bluebank.api.account.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

    private UUID id;
    
    private String agency;

    private String accountNumber;

	private String digit;

    private Double balance;

    private Double credit ;

}
