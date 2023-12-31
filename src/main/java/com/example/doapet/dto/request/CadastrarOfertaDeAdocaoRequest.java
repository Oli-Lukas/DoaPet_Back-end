package com.example.doapet.dto.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ALUNO
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CadastrarOfertaDeAdocaoRequest {

  private String  tituloAdocao;
  private String  descricaoAdocao;
  private String  nomeAnimal;
  private String  especieAnimal;
  private String  racaAnimal;
  private Double  pesoAnimal;
  private Integer idadeAnimal;
  private String  descricaoAnimal;
  private MultipartFile fotoAnimal;
}
