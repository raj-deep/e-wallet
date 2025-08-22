package com.project.epay.transactions.service;

import com.project.epay.common.models.Transaction;
import com.project.epay.transactions.repository.TransactionRepository;
import com.project.epay.transactions.util.TransactionValidation;
import com.project.epay.useraccounts.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountService accountService;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, AccountService accountService){
        this.accountService = accountService;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public boolean createTransaction(Transaction transaction){
        if(!TransactionValidation.isValidTransaction(transaction)){
            log.info("Invalid Transaction");
            return false;
        }
        this.accountService.sendMoney(transaction);
        this.transactionRepository.save(transaction);
        return true;
    }
}
