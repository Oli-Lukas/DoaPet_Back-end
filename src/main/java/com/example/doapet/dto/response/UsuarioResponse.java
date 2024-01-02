package com.example.doapet.dto.response;

import com.example.doapet.model.TipoUsuario;
import com.example.doapet.model.Usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponse {

  private Long id;
  private String nome;
  private String email;
  private TipoUsuario tipoUsuario;
  private String endereco;
  private String numeroTelefone;

  public UsuarioResponse(Usuario usuario) {

    this.id = usuario.getId();
    this.nome = usuario.getNome();
    this.email = usuario.getEmail();
    this.tipoUsuario = usuario.getTipoUsuario();
    this.endereco = usuario.getEndereco();
    this.numeroTelefone = usuario.getNumeroTelefone();
  }
}
