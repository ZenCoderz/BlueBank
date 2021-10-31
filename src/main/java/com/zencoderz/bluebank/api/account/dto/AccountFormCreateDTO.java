package com.zencoderz.bluebank.api.account.dto;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountFormCreateDTO {

    @Size(min=4, max=4)
    private String agency;

    @Size(min=1, max=10)
    private String accountNumber;

    @Size(min=1, max=1)
    private String digit;

    private Double credit = 0D;

}
