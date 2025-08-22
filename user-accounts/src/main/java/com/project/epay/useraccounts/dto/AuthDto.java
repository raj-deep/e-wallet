package com.project.epay.useraccounts.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
public class AuthDto{

    private String username;
    private String password;
}
