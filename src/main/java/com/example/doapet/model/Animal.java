/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.doapet.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;

import java.sql.Blob;

@Entity(name = "Animal")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String especie;

    private String raca;
    private double peso;
    private int idade;
    private String descricao;
    
    @Lob
    private Blob foto;
    
    @OneToOne(mappedBy = "animalAdocao")
    private Adocao ofertaAdocao;

    @Deprecated
    public Animal() {}

    public Animal(String nome, String especie,String raca, double peso, int idade, String descricao, Blob foto) {
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.peso = peso;
        this.idade = idade;
        this.descricao = descricao;
        this.foto = foto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Blob getFoto() {
        return foto;
    }

    public void setFoto(Blob foto) {
        this.foto = foto;
    }


    public Adocao getOfertaAdocao() {
        return ofertaAdocao;
    }

    public void setOfertaAdocao(Adocao ofertaAdocao) {
        this.ofertaAdocao = ofertaAdocao;
    }
}
