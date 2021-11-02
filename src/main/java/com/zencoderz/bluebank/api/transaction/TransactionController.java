package com.zencoderz.bluebank.api.transaction;

import java.net.URI;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.zencoderz.bluebank.api.transaction.dto.TransactionDTO;
import com.zencoderz.bluebank.api.transaction.dto.TransactionFormCreateDTO;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/transactions")
@AllArgsConstructor
public class TransactionController {

	private TransactionService transactionService;
	
	@PostMapping
    public ResponseEntity<TransactionDTO> createTransaction(
    		@RequestBody @Valid TransactionFormCreateDTO transactionFormCreateDTO) {
        TransactionDTO transactionDTO = this.transactionService.createTransaction(transactionFormCreateDTO);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path(
        		"/" + transactionDTO.getId()).toUriString());
        return ResponseEntity.created(uri).body(transactionDTO);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> getAccountById(@PathVariable("id") UUID id) {
        TransactionDTO transactionDTO = this.transactionService.getTransactionDTOById(id);
        return ResponseEntity.ok(transactionDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable UUID id){
        this.transactionService.deleteTransaction(id);
        return ResponseEntity.ok("Deleted");
    }
	
}
