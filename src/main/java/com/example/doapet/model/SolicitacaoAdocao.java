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

@Entity
public class SolicitacaoAdocao {

    public enum StatusSolicitacao {
        Pendente,
        Aprovado,
        Rejeitado;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private SolicitacaoAdocao.StatusSolicitacao statusSolicitacao;

    @ManyToOne
    private Animal animal;

    @ManyToOne
    private Usuario novoDono;

    @Deprecated
    public SolicitacaoAdocao() {
    }

    public SolicitacaoAdocao(Animal animal, Usuario novoDono) {
        this.animal = animal;
        this.novoDono = novoDono;
        this.statusSolicitacao = StatusSolicitacao.Pendente;
    }

    public Long getId() {
        return id;
    }

    public StatusSolicitacao getStatusSolicitacao() {
        return statusSolicitacao;
    }

    public void setStatusSolicitacao(StatusSolicitacao statusSolicitacao) {
        this.statusSolicitacao = statusSolicitacao;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Usuario getNovoDono() {
        return novoDono;
    }

    public void setNovoDono(Usuario novoDono) {
        this.novoDono = novoDono;
    }
}
