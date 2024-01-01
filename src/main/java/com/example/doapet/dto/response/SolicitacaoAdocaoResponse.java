package com.example.doapet.dto.response;

import com.example.doapet.model.SolicitacaoAdocao;
import com.example.doapet.model.StatusSolicitacao;
import com.example.doapet.model.TipoUsuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SolicitacaoAdocaoResponse {

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  class SolicitacaoGroupResponse {

    private Long id;
    private StatusSolicitacao statusSolicitacao;
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  class UsuarioGroupResponse {

    private Long id;
    private String nome;
    private String email;
    private TipoUsuario tipoUsuario;
  }

  private SolicitacaoGroupResponse solicitacao;
  private UsuarioGroupResponse solicitante;

  public SolicitacaoAdocaoResponse(SolicitacaoAdocao solicitacaoAdocao) {

    this.solicitacao = new SolicitacaoGroupResponse();
    this.solicitacao.setId(solicitacaoAdocao.getId());
    this.solicitacao.setStatusSolicitacao(solicitacaoAdocao.getStatusSolicitacao());

    this.solicitante = new UsuarioGroupResponse();
    this.solicitante.setId(solicitacaoAdocao.getNovoDono().getId());
    this.solicitante.setNome(solicitacaoAdocao.getNovoDono().getNome());
    this.solicitante.setEmail(solicitacaoAdocao.getNovoDono().getEmail());
    this.solicitante.setTipoUsuario(solicitacaoAdocao.getNovoDono().getTipoUsuario());
  }
}
