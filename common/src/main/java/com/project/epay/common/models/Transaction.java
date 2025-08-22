package com.project.epay.common.models;

//import com.project.epay.useraccounts.model.Account;
//import com.project.epay.useraccounts.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID transactionId;

    @ManyToOne
    private Account senderAccount;

    @ManyToOne
    private Account receiverAccount;

    private double amount;

    private boolean wasSuccessful;

    private Instant createdAt;
}
