/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.doapet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;


@Entity
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeDoEvento;
    private String descricao;
    private LocalDateTime dataDoEvento;
    private String endereco;

    @ManyToOne
    private Usuario organizador;

    public Evento(String nomeDoEvento, String descricao, LocalDateTime dataDoEvento, String endereco, Usuario organizador) {
        this.nomeDoEvento = nomeDoEvento;
        this.descricao = descricao;
        this.dataDoEvento = dataDoEvento;
        this.endereco = endereco;
        this.organizador = organizador;
    }
    
    @Deprecated
    public Evento() {
        
    }
    
    

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getNomeDoEvento() {
        return nomeDoEvento;
    }

    public void setNomeDoEvento(String nomeDoEvento) {
        this.nomeDoEvento = nomeDoEvento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataDoEvento() {
        return dataDoEvento;
    }

    public void setDataDoEvento(LocalDateTime dataDoEvento) {
        this.dataDoEvento = dataDoEvento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Usuario getOrganizador() {
        return organizador;
    }

    public void setOrganizador(Usuario organizador) {
        this.organizador = organizador;
    }
}

