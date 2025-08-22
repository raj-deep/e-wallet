package com.project.epay.transactions.util;

import com.project.epay.common.models.Transaction;

public abstract class TransactionValidation {

    public static boolean isValidTransaction(Transaction transaction){
        boolean amountValidation = transaction.getSenderAccount().getBalance() >= transaction.getAmount();
        boolean isKycCompleted =
                transaction.getSenderAccount().isKycCompleted() && transaction.getReceiverAccount().isKycCompleted();

        return amountValidation && isKycCompleted;
    }
}
