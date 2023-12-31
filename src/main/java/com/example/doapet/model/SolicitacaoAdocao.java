/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.doapet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "SolicitacaoAdocao")
public class SolicitacaoAdocao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private StatusSolicitacao statusSolicitacao;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario novoDono;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private OfertaAdocao ofertaAdocao;

    public SolicitacaoAdocao(
        StatusSolicitacao statusSolicitacao,
        Usuario novoDono
    ) {
        this.statusSolicitacao = statusSolicitacao;
        this.novoDono = novoDono;
    }
}
