package com.example.doapet.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Usuario")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = true)
    private String numeroTelefone;

    @OneToMany(
        mappedBy = "novoDono",
        fetch = FetchType.LAZY
    )
    private List<SolicitacaoAdocao> solicitacoes;

    @OneToMany(
        mappedBy = "organizador",
        fetch = FetchType.LAZY
    )
    private List<Evento> eventos;
    
    @OneToMany(
        mappedBy = "donoDaDivulgacao",
        fetch = FetchType.LAZY
    )
    private List<OfertaAdocao> divulgacoesDeAdocao;

    public Usuario(
        String nome,
        String email,
        String senha,
        TipoUsuario tipoUsuario,
        String endereco,
        String numeroTelefone
    ) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
        this.endereco = endereco;
        this.numeroTelefone = numeroTelefone;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.tipoUsuario == TipoUsuario.INDIVIDUAL) {
            return List.of(new SimpleGrantedAuthority("individual"));
        }else {
            return List.of(new SimpleGrantedAuthority("ong"));
        }
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) &&
               Objects.equals(nome, usuario.nome) &&
               Objects.equals(email, usuario.email) &&
               Objects.equals(senha, usuario.senha) &&
               tipoUsuario == usuario.tipoUsuario &&
               Objects.equals(endereco, usuario.endereco) &&
               Objects.equals(numeroTelefone, usuario.numeroTelefone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, email, senha, tipoUsuario, endereco, numeroTelefone);
    }
}

