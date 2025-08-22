package com.project.epay.common.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@With
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @JsonIgnore
    @OneToOne
    private User user;

    @Builder.Default
    private double balance = 0.0D;

    @Builder.Default
    private Instant createdAt = Instant.now();

    @Builder.Default
    private boolean kycCompleted = false;

    public void send(Account receiverAccount, double amount){
        this.balance -= amount;
        receiverAccount.balance += amount;
    }

}
