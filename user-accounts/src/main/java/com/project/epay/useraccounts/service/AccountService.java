package com.project.epay.useraccounts.service;

import com.project.epay.common.models.Transaction;
import com.project.epay.common.models.Account;
import com.project.epay.common.models.User;
import com.project.epay.useraccounts.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }
    public Account addAccount(User user){
        Account account = Account.builder()
                .user(user)
                .build();
        this.accountRepository.save(account);

        log.info("Account got created successfully");
        return account;
    }

    public Account getAccount(UUID accountID){
        Optional<Account> accountOptional = this.accountRepository.findById(accountID);
        return accountOptional.orElseThrow(()-> new RuntimeException("Account not found"));
    }

    public Account getAccountByUser(User user){
        return getAccountByUser(user.getId());
    }

    public Account getAccountByUser(UUID userId){
        Optional<Account> account = this.accountRepository.findByUserId(userId);
        return account.orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public void sendMoney(Transaction transaction){
        Account sender = transaction.getSenderAccount();
        Account receiver = transaction.getReceiverAccount();
        sender.send(receiver, transaction.getAmount());
        this.accountRepository.saveAll(List.of(sender, receiver));
    }
}
