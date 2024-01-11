package com.example.doapet.controllers;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.doapet.dto.response.SolicitacaoAdocaoResponse;
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

        Optional<Usuario> optionalCurrentUser = this.usuarioRepository.findByEmail(userDetails.getUsername());
        Optional<OfertaAdocao> optionalAdoptionOffer = this.ofertaAdocaoRepository.findById(idOfertaAdocao);

        if (optionalCurrentUser.isEmpty() || optionalAdoptionOffer.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Usuario currentUser = optionalCurrentUser.get();
        OfertaAdocao adoptionOffer = optionalAdoptionOffer.get();

        if (!(adoptionOffer.getSolicitacoesDeAdocao().isEmpty())) {
            for (SolicitacaoAdocao currentSA : adoptionOffer.getSolicitacoesDeAdocao()) {
                if (currentUser.equals(currentSA.getNovoDono())) {
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
                }
            }
        }

        SolicitacaoAdocao adoptionRequest = new SolicitacaoAdocao(StatusSolicitacao.PENDENTE, currentUser);

        adoptionRequest.setOfertaAdocao(adoptionOffer);
        adoptionOffer.getSolicitacoesDeAdocao().add(adoptionRequest);

        solicitacaoAdocaoRepository.save(adoptionRequest);
        ofertaAdocaoRepository.save(adoptionOffer);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping("/{idOfertaAdocao}")
    public ResponseEntity<List<SolicitacaoAdocaoResponse>> listarSolicitacoesDeAdocao(
            @PathVariable Long idOfertaAdocao
    ) {

        List<SolicitacaoAdocaoResponse> response = new ArrayList<>();

        Optional<OfertaAdocao> optionalAdoptionOffer = this.ofertaAdocaoRepository.findById(idOfertaAdocao);
        if (optionalAdoptionOffer.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        OfertaAdocao adoptionOffer = optionalAdoptionOffer.get();

        List<SolicitacaoAdocao> adoptionsRequests = adoptionOffer.getSolicitacoesDeAdocao();

        for (SolicitacaoAdocao adoptionRequest : adoptionsRequests) {
            response.add(new SolicitacaoAdocaoResponse(adoptionRequest));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/pending/{idOfertaAdocao}")
    public ResponseEntity<List<SolicitacaoAdocaoResponse>> listarSolicitacoesDeAdocaoPendentes(
            @PathVariable Long idOfertaAdocao
    ) {

        List<SolicitacaoAdocaoResponse> response = new ArrayList<>();
        Predicate<SolicitacaoAdocao> byStatusSolicitacao = solicitacao -> solicitacao.getStatusSolicitacao() == StatusSolicitacao.PENDENTE;

        Optional<OfertaAdocao> optionalAdoptionOffer = this.ofertaAdocaoRepository.findById(idOfertaAdocao);
        if (optionalAdoptionOffer.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        OfertaAdocao adoptionOffer = optionalAdoptionOffer.get();

        List<SolicitacaoAdocao> adoptionsRequests = adoptionOffer.getSolicitacoesDeAdocao();
        List<SolicitacaoAdocao> pendingAdoptionsRequests = adoptionsRequests.stream().filter(byStatusSolicitacao).collect(Collectors.toList());

        for (SolicitacaoAdocao adoptionRequest : pendingAdoptionsRequests) {
            response.add(new SolicitacaoAdocaoResponse(adoptionRequest));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PatchMapping("/accept/{idOfertaAdocao}/{idSolicitacaoAdocao}")
    public ResponseEntity<Void> aceitarSolicitacaoAdocao(
            @PathVariable Long idOfertaAdocao,
            @PathVariable Long idSolicitacaoAdocao
    ) {

        Optional<OfertaAdocao> optionalAdoptionOffer = this.ofertaAdocaoRepository.findById(idOfertaAdocao);
        Optional<SolicitacaoAdocao> optionalAdoptionRequest = this.solicitacaoAdocaoRepository.findById(idSolicitacaoAdocao);

        if (optionalAdoptionOffer.isEmpty() || optionalAdoptionRequest.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        OfertaAdocao adoptionOffer = optionalAdoptionOffer.get();
        SolicitacaoAdocao adoptionRequest = optionalAdoptionRequest.get();

        adoptionRequest.setStatusSolicitacao(StatusSolicitacao.APROVADO);
        this.solicitacaoAdocaoRepository.save(adoptionRequest);

        adoptionOffer.setStatusAdocao(StatusAdocao.APROVADO);
        this.ofertaAdocaoRepository.save(adoptionOffer);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @PatchMapping("/reject/{idOfertaAdocao}/{idSolicitacaoAdocao}")
    public ResponseEntity<Void> rejeitarSolicitacaoAdocao(
            @PathVariable Long idOfertaAdocao,
            @PathVariable Long idSolicitacaoAdocao
    ) {

        Optional<OfertaAdocao> optionalAdoptionOffer = this.ofertaAdocaoRepository.findById(idOfertaAdocao);
        Optional<SolicitacaoAdocao> optionalAdoptionRequest = this.solicitacaoAdocaoRepository.findById(idSolicitacaoAdocao);

        if (optionalAdoptionOffer.isEmpty() || optionalAdoptionRequest.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        OfertaAdocao adoptionOffer = optionalAdoptionOffer.get();
        SolicitacaoAdocao adoptionRequest = optionalAdoptionRequest.get();

        adoptionRequest.setStatusSolicitacao(StatusSolicitacao.REJEITADO);
        this.solicitacaoAdocaoRepository.save(adoptionRequest);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @GetMapping("/byUser/accept/{idUsuario}")
    public ResponseEntity<List<SolicitacaoAdocaoResponse>> solicitacoesAceitas(@PathVariable Long idUsuario) {

        List<SolicitacaoAdocaoResponse> response = new ArrayList<>();

        Optional<Usuario> optionalSolicitante = this.usuarioRepository.findById(idUsuario);
        if (optionalSolicitante.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        Usuario solicitante = optionalSolicitante.get();

        for (SolicitacaoAdocao solicitacao: solicitante.getSolicitacoes())
            if (solicitacao.getStatusSolicitacao() == StatusSolicitacao.APROVADO)
                response.add(new SolicitacaoAdocaoResponse(solicitacao));
        
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
