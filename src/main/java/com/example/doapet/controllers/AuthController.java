/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.doapet.controllers;

import com.example.doapet.dto.AuthenticationRequest;
import com.example.doapet.dto.AuthenticationResponse;
import com.example.doapet.dto.RegisterRequest;
import com.example.doapet.service.AuthenticationService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author euluc
 */

@RestController
@RequestMapping("auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;
    
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
        @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
    
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> cadastro(
        @RequestBody RegisterRequest request
    ) {

        return ResponseEntity.ok(authenticationService.register(request));
    }
}
