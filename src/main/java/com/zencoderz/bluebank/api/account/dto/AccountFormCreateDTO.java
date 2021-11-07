package com.zencoderz.bluebank.api.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountFormCreateDTO {
	
	@Size(min=4, max=4)
    @Schema(example = "4444")
    private String agency;

    @Size(min=1, max=10)
    @Schema(example = "1010101010")
    private String accountNumber;

    @Size(min=1, max=1)
    @Schema(example = "1")
    private String digit;

    @Schema(example = "500")
    private Double credit = 0D;

}
