package com.example.doapet.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    
    @Column(nullable = false)
    private String titulo;

    @Column(nullable = true)
    private String descricao;

    @Column(nullable = true)
    private String localizacao;
    
    @JsonIgnore
    @ManyToOne(
        fetch = FetchType.LAZY
    )
    private Usuario donoDaDivulgacao;
    
    @JsonIgnore
    @OneToMany(
        mappedBy = "ofertaAdocao",
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL
    )
    private List<SolicitacaoAdocao> solicitacoesDeAdocao;
    
    @JsonIgnore
    @OneToOne(
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL
    )
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
