package com.example.doapet.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AtualizarSenhaRequest {
  
  private String senhaAtual;
  private String novaSenha;
}
