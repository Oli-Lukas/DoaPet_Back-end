/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.doapet.controllers;

import com.example.doapet.config.JwtService;
import com.example.doapet.dto.CadastroDTO;
import com.example.doapet.dto.LoginDTO;
import com.example.doapet.dto.LoginResponseDTO;
import com.example.doapet.model.Usuario;
import com.example.doapet.repository.UsuarioRepository;
import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
public class AuthController {
    
    @Autowired
    private AuthenticationManager authManager;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private JwtService jwtService;
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginDTO data) {

        UsernamePasswordAuthenticationToken userNamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());

        Usuario usuario = usuarioRepository.findByEmail(data.email());

        var auth  = this.authManager.authenticate(userNamePassword);
        // var token = tokenService.generateToken(((Usuario)auth.getPrincipal()).getEmail());
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("email", data.email());

        String token = jwtService.generateToken(claims, usuario);
        
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new LoginResponseDTO(token));
    }
    
    @PostMapping("/cadastro")
    public ResponseEntity<Void> cadastro(@RequestBody @Valid CadastroDTO data) {

        if(this.usuarioRepository.findByEmail(data.email()) != null)
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        
        String  senhaCrip   = new BCryptPasswordEncoder().encode(data.senha());
        Usuario currentUser = new Usuario(data.nome(), data.email(), senhaCrip, data.tipoUsuario(), data.endereco(), data.telefone());
        
        this.usuarioRepository.save(currentUser);
        
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
