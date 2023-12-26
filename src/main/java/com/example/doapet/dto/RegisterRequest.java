package com.example.doapet.dto;

import com.example.doapet.model.TipoUsuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
 
  private String nome;
  private String email;
  private String senha;
  private String endereco;
  private String telefone;
  private TipoUsuario tipoUsuario;
}
