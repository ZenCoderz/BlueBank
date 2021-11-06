package com.zencoderz.bluebank.api.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountFormUpdateDTO {

	@Size(min=4, max=4)
    private String agency;

    @Size(min=1, max=10)
    private String accountNumber;

    @Size(min=1, max=1)
    private String digit;

    @Min(0)
    private Double credit = 0D;

}
