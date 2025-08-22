package com.project.epay.useraccounts.service;

import com.project.epay.common.models.User;
import com.project.epay.useraccounts.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final AccountService accountService;
//    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, AccountService accountService){
        this.userRepository = userRepository;
        this.accountService = accountService;
//        this.passwordEncoder = passwordEncoder;
    }

    @Transactional          //This method is transactional, which means either both user and account will be created or none of them would.
    public User addUser(User user){
        user.setRole("USER");
//        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        user = this.userRepository.save(user);
        this.accountService.addAccount(user);
        log.info("user and account is created");
        return user;
    }

    public User getUserById(UUID userId){
        Optional<User> optionalUser = this.userRepository.findById(userId);
        return optionalUser.orElseThrow(() -> new RuntimeException("User not found"));
    }
}
