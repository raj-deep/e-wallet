package com.project.epay.useraccounts.controller;

import com.project.epay.useraccounts.dto.AuthDto;
import com.project.epay.common.models.User;
import com.project.epay.useraccounts.service.UserService;
//import com.project.epay.useraccounts.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/")
public class AuthController {

//    private final JwtUtil jwtUtil;
//    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService){
//        this.authenticationManager = authenticationManager;
//        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthDto authDto){
//        Authentication authentication = this.authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword()));
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        String jwtToken = this.jwtUtil.generateToken(userDetails);
//        return ResponseEntity.ok(new AuthResponse(userDetails, jwtToken));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/signup")
    public ResponseEntity<?> addMember(@RequestBody User user) {
        User userToAdd = this.userService.addUser(user);
//        UserDetails userDetails = new org.springframework.security.core.userdetails.User(userToAdd.getUsername(), userToAdd.getPassword(),
//                Collections.singletonList(new SimpleGrantedAuthority(
//                        user.getRole()
//                )));
//        String jwtToken = this.jwtUtil.generateToken(userDetails);
//        return ResponseEntity.ok(new AuthResponse(userDetails, jwtToken));
        return ResponseEntity.ok(userToAdd);
    }


}
