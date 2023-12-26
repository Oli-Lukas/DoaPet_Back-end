package com.example.doapet.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.doapet.dto.AuthenticationRequest;
import com.example.doapet.dto.AuthenticationResponse;
import com.example.doapet.dto.RegisterRequest;
import com.example.doapet.model.Usuario;
import com.example.doapet.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final UsuarioRepository usuarioRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(RegisterRequest request) {

    Usuario user = Usuario.builder()
                            .nome(request.getNome())
                            .email(request.getEmail())
                            .senha(passwordEncoder.encode(request.getSenha()))
                            .endereco(request.getEndereco())
                            .numeroTelefone(request.getTelefone())
                            .tipoUsuario(request.getTipoUsuario())
                            .build();
    
    usuarioRepository.save(user);
    String jwtToken = jwtService.generateToken(user);

    return AuthenticationResponse.builder()
            .token(jwtToken)
            .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {

    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        request.getEmail(),
        request.getSenha()
      )
    );

    Usuario user = usuarioRepository.findByEmail(request.getEmail()).orElseThrow();
    String jwtToken = jwtService.generateToken(user);
    
    return AuthenticationResponse.builder()
            .token(jwtToken)
            .build();
  }
}
