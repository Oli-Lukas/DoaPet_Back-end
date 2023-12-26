/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.doapet.controllers;

import com.example.doapet.dto.AdocaoDTO;
import com.example.doapet.model.Adocao;
import com.example.doapet.model.Animal;
import com.example.doapet.model.StatusAdocao;
import com.example.doapet.model.Usuario;
import com.example.doapet.repository.AdocaoRepository;
import com.example.doapet.repository.AnimalRepository;
import com.example.doapet.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("adocao")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AdocaoAndAnimalController {

    private final UsuarioRepository usuarioRepository;
    private final AdocaoRepository adocaoRepository;
    private final AnimalRepository animalRepository;

    @PostMapping("/oferta")
    @PreAuthorize("hasAnyAuthority('individual', 'ong')")
    public ResponseEntity<Void> cadastrarAdocaoEAnimal(@ModelAttribute AdocaoDTO adocaoDTO) throws IOException, SQLException
    {
        System.out.println("Executou!!!");
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                                                    .getContext()
                                                    .getAuthentication()
                                                    .getPrincipal();
        
        Usuario currentUsuario = (Usuario) usuarioRepository.findByEmail(userDetails.getUsername()).get();
        
        byte bytes[] = adocaoDTO.fotoAnimal().getBytes();
        Blob blob    = new javax.sql.rowset.serial.SerialBlob(bytes);

        Animal currentAnimal = new Animal(
            adocaoDTO.nomeAnimal(),
            adocaoDTO.especieAnimal(),
            adocaoDTO.racaAnimal(),
            adocaoDTO.pesoAnimal(),
            adocaoDTO.idadeAnimal(),
            adocaoDTO.descricaoAnimal(),
            blob
        );
        Adocao currentAdocao = new Adocao(
            adocaoDTO.tituloAdocao(),
            adocaoDTO.descricaoAdocao(),
            currentUsuario,
            currentAnimal
        );
        
        animalRepository.save(currentAnimal);
        adocaoRepository.save(currentAdocao);
        
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
    
    @GetMapping("/adocoes-disponiveis")
    public ResponseEntity<List<Adocao>> getAdocoesDisponiveis() {
        List<Adocao> currentAdocoes = adocaoRepository.findByStatusAdocao(StatusAdocao.PENDENTE);
        return ResponseEntity.status(HttpStatus.OK).body(currentAdocoes);
    }

}
