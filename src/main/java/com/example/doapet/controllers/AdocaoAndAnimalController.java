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
import jakarta.validation.Valid;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class AdocaoAndAnimalController {

    @Autowired
    private UsuarioRepository repoUsuario;
    
    @Autowired
    private AdocaoRepository repoAdocao;
    
    @Autowired
    private AnimalRepository repoAnimal;

    @PostMapping("/oferta")
    public ResponseEntity cadastrarAdocaoEAnimal(@ModelAttribute @Valid AdocaoDTO data) throws IOException, SQLException {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Usuario currentUsuario = (Usuario) repoUsuario.findByEmail(userDetails.getUsername());

        
        
        byte bytes[] = data.fotoAnim().getBytes();
        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
        Animal currentAnimal = new Animal(data.nomeAnim(),data.especieAnim(), data.racaAnim(), data.pesoAnim(), data.idadeAnim(), data.descricaoAnim(), blob);
        
        
        Adocao currentAdocao = new Adocao(data.tituloAdoc(),data.descricaoAdoc(),currentUsuario, currentAnimal);
        
        repoAnimal.save(currentAnimal);
        repoAdocao.save(currentAdocao);
        
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/adocoes-disponiveis")
    public ResponseEntity<List<Adocao>> getAdocoesDisponiveis() {
        List<Adocao> currentAdocoes = repoAdocao.findByStatusAdocao(StatusAdocao.PENDENTE);
        return ResponseEntity.status(HttpStatus.OK).body(currentAdocoes);
    }

}
