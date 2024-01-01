package com.example.doapet.dto.request;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CadastrarEventoRequest {
  
  private String nomeDoEvento;
  private String descricao;
  private LocalDateTime dataDoEvento;
}
