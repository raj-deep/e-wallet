package com.project.epay.useraccounts.controller;

import com.project.epay.common.models.Account;
import com.project.epay.useraccounts.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/account/")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @GetMapping("/get_info")
    public ResponseEntity<Account> getAccountInfoByUser(@RequestParam UUID userId){
        try {
            Account account = this.accountService.getAccountByUser(userId);
            return new ResponseEntity<>(account, HttpStatus.OK);
        }catch(RuntimeException exception){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/get_info/{accountId}")
    public ResponseEntity<Account> getAccountInfo(@PathVariable UUID accountId){
        try {
            Account account = this.accountService.getAccount(accountId);
            return new ResponseEntity<>(account, HttpStatus.OK);
        }catch (RuntimeException exception){
            return ResponseEntity.notFound().build();
        }
    }
}
