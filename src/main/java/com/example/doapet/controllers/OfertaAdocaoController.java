package com.example.doapet.controllers;

import com.example.doapet.dto.request.CadastrarOfertaDeAdocaoRequest;
import com.example.doapet.dto.response.OfertaAdocaoResponse;
import com.example.doapet.model.Animal;
import com.example.doapet.model.OfertaAdocao;
import com.example.doapet.model.SolicitacaoAdocao;
import com.example.doapet.model.StatusAdocao;
import com.example.doapet.model.Usuario;
import com.example.doapet.repository.OfertaAdocaoRepository;
import com.example.doapet.repository.SolicitacaoAdocaoRepository;
import com.example.doapet.repository.AnimalRepository;
import com.example.doapet.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("oferta-adocao")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class OfertaAdocaoController {

    private final UsuarioRepository usuarioRepository;
    private final OfertaAdocaoRepository adocaoRepository;
    private final AnimalRepository animalRepository;
    private final SolicitacaoAdocaoRepository solicitacaoRepository;

    @PostMapping("/cadastrar")
    public ResponseEntity<Void> cadastrarOfertaDeAdocao(
        @RequestBody @ModelAttribute CadastrarOfertaDeAdocaoRequest ofertaAdocaoRequest

    ) throws IOException, SQLException {

        UserDetails userDetails = (UserDetails) SecurityContextHolder
                                                    .getContext()
                                                    .getAuthentication()
                                                    .getPrincipal();
        
        Usuario currentUsuario = (Usuario) usuarioRepository.findByEmail(userDetails.getUsername()).get();
        
        byte bytes[] = Base64.getEncoder().encode(ofertaAdocaoRequest.getFotoAnimal().getBytes());
        Blob blob    = new SerialBlob(bytes);

        Animal currentAnimal = new Animal(
            ofertaAdocaoRequest.getNomeAnimal(),
            ofertaAdocaoRequest.getEspecieAnimal(),
            ofertaAdocaoRequest.getRacaAnimal(),
            ofertaAdocaoRequest.getPesoAnimal(),
            ofertaAdocaoRequest.getIdadeAnimal(),
            ofertaAdocaoRequest.getDescricaoAnimal(),
            blob
        );
        OfertaAdocao currentAdocao = new OfertaAdocao(
            ofertaAdocaoRequest.getTituloAdocao(),
            ofertaAdocaoRequest.getDescricaoAdocao(),
            currentUsuario,
            currentAnimal
        );
        
        animalRepository.save(currentAnimal);
        adocaoRepository.save(currentAdocao);
        
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
    
    @GetMapping("/")
    public ResponseEntity<List<OfertaAdocaoResponse>> listarOfertasDeAdocaoPendentes() throws SQLException {

        List<OfertaAdocaoResponse> response = new ArrayList<>();
        List<OfertaAdocao> currentAdocoes = adocaoRepository.findByStatusAdocao(StatusAdocao.PENDENTE);

        for (OfertaAdocao ofertaAdocao: currentAdocoes)
            response.add(new OfertaAdocaoResponse(ofertaAdocao));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{idOfertaAdocao}")
    public ResponseEntity<OfertaAdocaoResponse> lerOfertaAdocao(@PathVariable Long idOfertaAdocao) throws SQLException {

        Optional<OfertaAdocao> optionalOfertaAdocao = this.adocaoRepository.findById(idOfertaAdocao);
        if (optionalOfertaAdocao.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        OfertaAdocaoResponse response = new OfertaAdocaoResponse(optionalOfertaAdocao.get());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/byAdoptionRequest/{idSolicitacaoAdocao}")
    public ResponseEntity<OfertaAdocaoResponse> lerOfertaAdocaoPorSolicitacaoAdocao(@PathVariable Long idSolicitacaoAdocao) throws SQLException {

        Optional<SolicitacaoAdocao> optionalSolicitacaoAdocao = this.solicitacaoRepository.findById(idSolicitacaoAdocao);
        if (optionalSolicitacaoAdocao.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        SolicitacaoAdocao solicitacaoAdocao = optionalSolicitacaoAdocao.get();

        Iterable<OfertaAdocao> ofertasDeAdocao = this.adocaoRepository.findAll();
        for (OfertaAdocao ofertaAdocao: ofertasDeAdocao)
            if (ofertaAdocao.getSolicitacoesDeAdocao().contains(solicitacaoAdocao))
                return ResponseEntity.status(HttpStatus.OK).body(new OfertaAdocaoResponse(ofertaAdocao));
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
