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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Evento")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeDoEvento;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataDoEvento;

    @Column(nullable = false)
    private String endereco;

    @ManyToOne
    private Usuario organizador;

    public Evento(
        String nomeDoEvento,
        String descricao,
        LocalDateTime dataDoEvento,
        String endereco,
        Usuario organizador
    ) {

        this.nomeDoEvento = nomeDoEvento;
        this.descricao = descricao;
        this.dataDoEvento = dataDoEvento;
        this.endereco = endereco;
        this.organizador = organizador;
    }
}
