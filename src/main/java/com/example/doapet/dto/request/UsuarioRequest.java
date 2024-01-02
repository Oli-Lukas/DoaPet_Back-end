package com.example.doapet.dto.request;

import com.example.doapet.model.Usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequest {
  
  private String newNome;
  private String newEmail;
  private String newEndereco;
  private String newNumeroTelefone;

  public void merge(Usuario usuario) {

    if (validString(newNome))           usuario.setNome(newNome);
    if (validString(newEmail))          usuario.setEmail(newEmail);
    if (validString(newEndereco))       usuario.setEndereco(newEndereco);
    if (validString(newNumeroTelefone)) usuario.setNumeroTelefone(newNumeroTelefone);
  }

  private boolean validString(String param) {
    return (param != null && !param.isBlank() && !param.isEmpty());
  }
}
