package com.zencoderz.bluebank.api.account;

import com.zencoderz.bluebank.api.account.dto.AccountDTO;
import com.zencoderz.bluebank.api.account.dto.AccountFormCreateDTO;
import com.zencoderz.bluebank.api.account.dto.AccountFormUpdateDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

import java.net.URI;
import java.util.Set;


@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
@Tag(name = "Account")
public class AccountController {

    private AccountService accountService;

    @GetMapping
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<Set<AccountDTO>> getAccounts() {
        Set<AccountDTO> accountDTOS = this.accountService.getAccountsDTO();
        return ResponseEntity.ok(accountDTOS);
    }

    @GetMapping("/{id}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<AccountDTO> findAccountById(@PathVariable("id") Long id) {
        AccountDTO accountDTO = this.accountService.findAccountDTOById(id);
        return ResponseEntity.ok(accountDTO);
    }

    @PostMapping("/user/{userId}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<AccountDTO> createAccount(@PathVariable("userId") Long userId,
                                                    @RequestBody @Valid AccountFormCreateDTO accountFormCreateDTO) {
        AccountDTO accountDTO = this.accountService.createAccount(userId, accountFormCreateDTO);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/" + accountDTO.getId()).toUriString());
        return ResponseEntity.created(uri).body(accountDTO);
    }

    @PutMapping("/{id}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<AccountDTO> updateAccount(@PathVariable("id") Long id,
                                                    @RequestBody @Valid AccountFormUpdateDTO accountFormUpdateDTO) {
        AccountDTO accountDTO = this.accountService.updateAccount(id, accountFormUpdateDTO);
        return ResponseEntity.ok(accountDTO);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        this.accountService.deleteAccount(id);
        return ResponseEntity.ok("Deleted");
    }

}
