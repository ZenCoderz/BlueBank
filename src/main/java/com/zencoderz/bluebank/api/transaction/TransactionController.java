package com.zencoderz.bluebank.api.transaction;

import java.net.URI;
import java.util.Set;
import java.util.UUID;

import javax.validation.Valid;

import com.zencoderz.bluebank.api.account.dto.AccountDTO;
import com.zencoderz.bluebank.api.account.dto.AccountFormUpdateDTO;
import com.zencoderz.bluebank.api.transaction.dto.TransactionFormUpdateDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/" + transactionDTO.getId()).toUriString());
        return ResponseEntity.created(uri).body(transactionDTO);
    }

    @GetMapping
    public ResponseEntity<Set<TransactionDTO>> getTransactions() {
        Set<TransactionDTO> transactionDTOS = this.transactionService.getTransactionsDTO();
        return ResponseEntity.ok(transactionDTOS);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> findTransactionById(@PathVariable("id") UUID id) {
        TransactionDTO transactionDTO = this.transactionService.findTransactionDTOById(id);
        return ResponseEntity.ok(transactionDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionDTO> updateTransaction(@PathVariable("id") UUID id,
                                                    @RequestBody @Valid TransactionFormUpdateDTO transactionFormUpdateDTO) {
        TransactionDTO transactionDTO = this.transactionService.updateTransaction(id, transactionFormUpdateDTO);
        return ResponseEntity.ok(transactionDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable UUID id){
        this.transactionService.deleteTransaction(id);
        return ResponseEntity.ok("Deleted");
    }
	
}
