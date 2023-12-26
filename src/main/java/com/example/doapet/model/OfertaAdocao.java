/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.doapet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "OfertaAdocao")
public class OfertaAdocao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    private StatusAdocao statusAdocao;
    
    private String titulo;
    private String descricao;
    private String localizacao;
    
    @ManyToOne
    private Usuario donoDaDivulgacao;
    
    @OneToMany(mappedBy = "ofertaAdocao")
    private List<SolicitacaoAdocao> solicitacoesDeAdocao;
    
    @OneToOne
    private Animal animalAdocao;

    public OfertaAdocao(
        String titulo,
        String descricao,
        Usuario donoDaDivulgacao,
        Animal animalAdocao
    ) {
        this.statusAdocao = StatusAdocao.PENDENTE;
        this.titulo = titulo;
        this.descricao = descricao;
        this.donoDaDivulgacao = donoDaDivulgacao;
        this.localizacao = this.donoDaDivulgacao.getEndereco();
        this.animalAdocao = animalAdocao;
    }
}
