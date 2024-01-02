package com.example.doapet.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.doapet.dto.request.UsuarioRequest;
import com.example.doapet.dto.response.UsuarioResponse;
import com.example.doapet.model.Usuario;
import com.example.doapet.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("usuario")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UsuarioController {
  
  private final UsuarioRepository usuarioRepository;

  @GetMapping("/{idUsuario}")
  public ResponseEntity<UsuarioResponse> lerDadosUsuario(@PathVariable Long idUsuario) {

    Optional<Usuario> optionalUsuario = this.usuarioRepository.findById(idUsuario);
    if (optionalUsuario.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    Usuario usuario = optionalUsuario.get();

    return ResponseEntity.status(HttpStatus.OK).body(new UsuarioResponse(usuario));
  }

  @PatchMapping("/{idUsuario}")
  public ResponseEntity<Void> atualizarDadosDeUsuario(
    @PathVariable Long idUsuario,
    @RequestBody UsuarioRequest request
  ) {

    Optional<Usuario> optionalUsuario = this.usuarioRepository.findById(idUsuario);
    if (optionalUsuario.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    Usuario usuario = optionalUsuario.get();

    request.merge(usuario);
    this.usuarioRepository.save(usuario);

    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
