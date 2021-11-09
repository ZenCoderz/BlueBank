package com.zencoderz.bluebank.api.transaction;

import java.net.URI;
import java.util.List;
import java.util.Set;


import javax.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.zencoderz.bluebank.api.transaction.dto.TransactionDTO;
import com.zencoderz.bluebank.api.transaction.dto.TransactionFormCreateDTO;
import com.zencoderz.bluebank.api.transaction.dto.TransactionFormUpdateDTO;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/transactions")
@AllArgsConstructor
@Tag(name = "Transaction")
public class TransactionController {

	private TransactionService transactionService;
	
	@PostMapping
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<TransactionDTO> createTransaction(
            @RequestBody @Valid TransactionFormCreateDTO transactionFormCreateDTO) {
        TransactionDTO transactionDTO = this.transactionService.createTransaction(transactionFormCreateDTO);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/" + transactionDTO.getId()).toUriString());
        return ResponseEntity.created(uri).body(transactionDTO);
    }

    @GetMapping
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<List<TransactionDTO>> getTransactions() {
        List<TransactionDTO> transactionDTOS = this.transactionService.getTransactionsDTO();
        return ResponseEntity.ok(transactionDTOS);
    }
	
	@GetMapping("/{id}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<TransactionDTO> findTransactionById(@PathVariable("id") Long id) {
        TransactionDTO transactionDTO = this.transactionService.findTransactionDTOById(id);
        return ResponseEntity.ok(transactionDTO);
    }

    @GetMapping("/account/{accountId}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<List<TransactionDTO>> findTransactionsByAccountId(@PathVariable("accountId") Long accountId) {
        List<TransactionDTO> transactionsDTO = this.transactionService.getTransactionsByAccountId(accountId);
        return ResponseEntity.ok(transactionsDTO);
    }

    @PutMapping("/{id}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<TransactionDTO> updateTransaction(@PathVariable("id") Long id,
                                                    @RequestBody @Valid TransactionFormUpdateDTO transactionFormUpdateDTO) {
        TransactionDTO transactionDTO = this.transactionService.updateTransaction(id, transactionFormUpdateDTO);
        return ResponseEntity.ok(transactionDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<String> deleteTransaction(@PathVariable Long id){
        this.transactionService.deleteTransaction(id);
        return ResponseEntity.ok("Deleted");
    }
	
}
