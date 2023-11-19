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

import java.util.List;

@Entity(name = "Adocao")
public class Adocao {
    
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

    @Deprecated
    public Adocao() {}

    public Adocao(String titulo, String descricao, Usuario donoDaDivulgacao, Animal animalAdocao) {
        this.statusAdocao = StatusAdocao.PENDENTE;
        this.titulo = titulo;
        this.descricao = descricao;
        this.donoDaDivulgacao = donoDaDivulgacao;
        this.localizacao = this.donoDaDivulgacao.getEndereco();
        this.animalAdocao = animalAdocao;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusAdocao getStatusAdocao() {
        return statusAdocao;
    }

    public void setStatusAdocao(StatusAdocao statusAdocao) {
        this.statusAdocao = statusAdocao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public Usuario getDonoDaDivulgacao() {
        return donoDaDivulgacao;
    }

    public void setDonoDaDivulgacao(Usuario donoDaDivulgacao) {
        this.donoDaDivulgacao = donoDaDivulgacao;
    }

    public List<SolicitacaoAdocao> getSolicitacoesDeAdocao() {
        return solicitacoesDeAdocao;
    }

    public void setSolicitacoesDeAdocao(List<SolicitacaoAdocao> solicitacoesDeAdocao) {
        this.solicitacoesDeAdocao = solicitacoesDeAdocao;
    }

    public Animal getAnimalAdocao() {
        return animalAdocao;
    }

    public void setAnimalAdocao(Animal animalAdocao) {
        this.animalAdocao = animalAdocao;
    }
}
