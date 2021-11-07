package com.zencoderz.bluebank.api.transaction.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.validation.constraints.DecimalMin;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionFormUpdateDTO {

    @DecimalMin("0.01")
    private Double amount;

}