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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    private Double peso;
    private Integer idade;
    private String descricao;
    
    @Lob
    private Blob foto;
    
    @OneToOne(mappedBy = "animalAdocao")
    private Adocao ofertaAdocao;

    public Animal(
        String nome,
        String especie,
        String raca,
        double peso,
        int idade,
        String descricao,
        Blob foto
    ) {
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.peso = peso;
        this.idade = idade;
        this.descricao = descricao;
        this.foto = foto;
    }
}
