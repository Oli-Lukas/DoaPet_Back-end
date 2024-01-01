package com.example.doapet.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.doapet.model.OfertaAdocao;
import com.example.doapet.model.SolicitacaoAdocao;
import com.example.doapet.model.StatusAdocao;
import com.example.doapet.model.StatusSolicitacao;
import com.example.doapet.model.Usuario;
import com.example.doapet.repository.OfertaAdocaoRepository;
import com.example.doapet.repository.SolicitacaoAdocaoRepository;
import com.example.doapet.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("solicitacao-adocao")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class SolicitacaoAdocaoController {

  private final SolicitacaoAdocaoRepository solicitacaoAdocaoRepository;
  private final OfertaAdocaoRepository ofertaAdocaoRepository;
  private final UsuarioRepository usuarioRepository;

  @PostMapping("/{idOfertaAdocao}")
  public ResponseEntity<Void> cadastrarSolicitacaoDeAdocao(
    @PathVariable Long idOfertaAdocao
  ) {

    UserDetails userDetails = (UserDetails) SecurityContextHolder
                                              .getContext()
                                              .getAuthentication()
                                              .getPrincipal();
    
    Usuario currentUsuario = (Usuario) usuarioRepository.findByEmail(userDetails.getUsername()).get();
    OfertaAdocao ofertaAdocao = ofertaAdocaoRepository.findById(idOfertaAdocao).get();

    SolicitacaoAdocao solicitacaoAdocao = new SolicitacaoAdocao(StatusSolicitacao.PENDENTE, currentUsuario);

    solicitacaoAdocao.setOfertaAdocao(ofertaAdocao);
    ofertaAdocao.getSolicitacoesDeAdocao().add(solicitacaoAdocao);

    solicitacaoAdocaoRepository.save(solicitacaoAdocao);
    ofertaAdocaoRepository.save(ofertaAdocao);

    return ResponseEntity
            .status(HttpStatus.OK)
            .build();
  }

  @GetMapping("/{idOfertaAdocao}")
  public ResponseEntity<List<SolicitacaoAdocao>> listarSolicitacoesDeAdocao(
    @PathVariable Long idOfertaAdocao
  ) {

    OfertaAdocao ofertaAdocao = this.ofertaAdocaoRepository.findById(idOfertaAdocao).get();
    List<SolicitacaoAdocao> solicitacoes = ofertaAdocao.getSolicitacoesDeAdocao();

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(solicitacoes);
  }

  // @GetMapping("/pending/{idOfertaAdocao}")
  // public ResponseEntity<List<SolicitacaoAdocao>> listarSolicitacoesDeAdocaoPendentes(
  //   @PathVariable Long idOfertaAdocao
  // ) {
  //   OfertaAdocao ofertaAdocao = this.ofertaAdocaoRepository.findById(idOfertaAdocao).get();
  //   List<SolicitacaoAdocao> solicitacoes = ofertaAdocao.getSolicitacoesDeAdocao();
  //   List<SolicitacaoAdocao> solicitacoesPendentes = solicitacoes
  //                                                     .stream()
  //                                                     .filter(solicitacao -> )
  //   return null;
  // }

  @PatchMapping("/accept/{idOfertaAdocao}/{idSolicitacaoAdocao}")
  public ResponseEntity<Void> aceitarSolicitacaoAdocao(
    @PathVariable Long idOfertaAdocao,
    @PathVariable Long idSolicitacaoAdocao
  ) {

    OfertaAdocao ofertaAdocao = this.ofertaAdocaoRepository.findById(idOfertaAdocao).get();
    SolicitacaoAdocao solicitacaoAdocao = this.solicitacaoAdocaoRepository.findById(idSolicitacaoAdocao).get();

    solicitacaoAdocao.setStatusSolicitacao(StatusSolicitacao.APROVADO);
    this.solicitacaoAdocaoRepository.save(solicitacaoAdocao);

    ofertaAdocao.setStatusAdocao(StatusAdocao.APROVADO);
    this.ofertaAdocaoRepository.save(ofertaAdocao);

    return ResponseEntity
            .status(HttpStatus.OK)
            .build();
  }

  @PatchMapping("/reject/{idOfertaAdocao}/{idSolicitacaoAdocao}")
  public ResponseEntity<Void> rejeitarSolicitacaoAdocao(
    @PathVariable Long idOfertaAdocao,
    @PathVariable Long idSolicitacaoAdocao
  ) {

    OfertaAdocao ofertaAdocao = this.ofertaAdocaoRepository.findById(idOfertaAdocao).get();
    SolicitacaoAdocao solicitacaoAdocao = this.solicitacaoAdocaoRepository.findById(idSolicitacaoAdocao).get();

    solicitacaoAdocao.setStatusSolicitacao(StatusSolicitacao.REJEITADO);
    this.solicitacaoAdocaoRepository.save(solicitacaoAdocao);

    return ResponseEntity
            .status(HttpStatus.OK)
            .build();
  }
}
