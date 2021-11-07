package com.zencoderz.bluebank.api.transaction;

import java.net.URI;
import java.util.Set;
import java.util.UUID;

import javax.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.zencoderz.bluebank.api.transaction.dto.TransactionDTO;
import com.zencoderz.bluebank.api.transaction.dto.TransactionFormCreateDTO;

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
    public ResponseEntity<Set<TransactionDTO>> getTransactions() {
        Set<TransactionDTO> transactionDTOS = this.transactionService.getTransactionsDTO();
        return ResponseEntity.ok(transactionDTOS);
    }
	
	@GetMapping("/{id}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<TransactionDTO> findTransactionById(@PathVariable("id") UUID id) {
        TransactionDTO transactionDTO = this.transactionService.findTransactionDTOById(id);
        return ResponseEntity.ok(transactionDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<String> deleteTransaction(@PathVariable UUID id){
        this.transactionService.deleteTransaction(id);
        return ResponseEntity.ok("Deleted");
    }
	
}
