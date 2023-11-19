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

@Entity(name = "SolicitacaoAdocao")
public class SolicitacaoAdocao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private StatusSolicitacao statusSolicitacao;

    @ManyToOne
    private Usuario novoDono;
    
    @ManyToOne
    private Adocao ofertaAdocao;

    @Deprecated
    public SolicitacaoAdocao() {}

    public SolicitacaoAdocao(Adocao ofertaAdocao, Usuario novoDono) {
        this.ofertaAdocao = ofertaAdocao;
        this.statusSolicitacao = StatusSolicitacao.PENDENTE;
        this.novoDono = novoDono;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public StatusSolicitacao getStatusSolicitacao() {
        return statusSolicitacao;
    }

    public void setStatusSolicitacao(StatusSolicitacao statusSolicitacao) {
        this.statusSolicitacao = statusSolicitacao;
    }

    public Usuario getNovoDono() {
        return novoDono;
    }

    public void setNovoDono(Usuario novoDono) {
        this.novoDono = novoDono;
    }

    public Adocao getOfertaAdocao() {
        return ofertaAdocao;
    }

    public void setOfertaAdocao(Adocao ofertaAdocao) {
        this.ofertaAdocao = ofertaAdocao;
    }
}
