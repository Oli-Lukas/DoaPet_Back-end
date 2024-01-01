package com.example.doapet.dto.response;

import java.time.LocalDateTime;

import com.example.doapet.model.Evento;
import com.example.doapet.model.TipoUsuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventoResponse {
  
  @Data
  @AllArgsConstructor
  class EventoGroupResponse {
    
    private Long id;
    private String nomeDoEvento;
    private String descricao;
    private LocalDateTime dataDoEvento;
    private String endereco;
  }

  @Data
  @AllArgsConstructor
  class UsuarioGroupResponse {

    private Long id;
    private String nome;
    private String email;
    private TipoUsuario tipoUsuario;
  }

  private EventoGroupResponse evento;
  private UsuarioGroupResponse organizador;

  public EventoResponse(Evento evento) {

    this.evento = new EventoGroupResponse(
      evento.getId(),
      evento.getNomeDoEvento(),
      evento.getDescricao(),
      evento.getDataDoEvento(),
      evento.getEndereco()
    );

    this.organizador = new UsuarioGroupResponse(
      evento.getOrganizador().getId(),
      evento.getOrganizador().getNome(),
      evento.getOrganizador().getEmail(),
      evento.getOrganizador().getTipoUsuario()
    );
  }
}
