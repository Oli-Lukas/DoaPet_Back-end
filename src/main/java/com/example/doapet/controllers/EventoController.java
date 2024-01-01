package com.example.doapet.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.doapet.dto.request.CadastrarEventoRequest;
import com.example.doapet.model.Evento;
import com.example.doapet.model.TipoUsuario;
import com.example.doapet.model.Usuario;
import com.example.doapet.repository.EventoRepository;
import com.example.doapet.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("evento")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class EventoController {
  
  private final EventoRepository eventoRepository;
  private final UsuarioRepository usuarioRepository;

  @PostMapping("/")
  public ResponseEntity<Void> cadastrarEvento(@RequestBody CadastrarEventoRequest request) {

    UserDetails userDetails =(UserDetails) SecurityContextHolder
                                            .getContext()
                                            .getAuthentication()
                                            .getPrincipal();

    Optional<Usuario> optionalCurrentUser = this.usuarioRepository.findByEmail(userDetails.getUsername());
    if (optionalCurrentUser.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    Usuario currentUser = optionalCurrentUser.get();
    if (currentUser.getTipoUsuario() == TipoUsuario.INDIVIDUAL) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    Evento evento = new Evento(
      request.getNomeDoEvento(),
      request.getDescricao(),
      request.getDataDoEvento(),
      currentUser.getEndereco(),
      currentUser
    );
    this.eventoRepository.save(evento);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
