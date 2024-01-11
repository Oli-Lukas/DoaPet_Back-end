package com.example.doapet.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.doapet.dto.request.CadastrarEventoRequest;
import com.example.doapet.dto.response.EventoResponse;
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

  @DeleteMapping("/{idEvento}")
  public ResponseEntity<Void> removerEvento(@PathVariable Long idEvento) {
    
    this.eventoRepository.deleteById(idEvento);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @GetMapping("/")
  public ResponseEntity<List<EventoResponse>> listarTodosEventosFuturos() {
    
    List<EventoResponse> response = new ArrayList<>();
    Predicate<Evento> byDateTime = evento -> LocalDateTime.now().isBefore(evento.getDataDoEvento());

    List<Evento> eventos = new ArrayList<>();
    this.eventoRepository.findAll().forEach(eventos::add);
    List<Evento> eventosFuturos = eventos.stream().filter(byDateTime).collect(Collectors.toList());

    for (Evento evento: eventosFuturos)
      response.add(new EventoResponse(evento));
    
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @GetMapping("/{idEvento}")
  public ResponseEntity<EventoResponse> lerEvento(@PathVariable Long idEvento) {

    Optional<Evento> optionalEvento = this.eventoRepository.findById(idEvento);
    if (optionalEvento.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    Evento evento = optionalEvento.get();

    return ResponseEntity.status(HttpStatus.OK).body(new EventoResponse(evento));
  }
}
