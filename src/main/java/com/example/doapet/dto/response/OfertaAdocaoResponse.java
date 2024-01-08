package com.example.doapet.dto.response;

import com.example.doapet.model.OfertaAdocao;
import com.example.doapet.model.StatusAdocao;
import com.example.doapet.model.TipoUsuario;
import java.sql.SQLException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OfertaAdocaoResponse {

    @Data
    @AllArgsConstructor
    class OfertaAdocaoGroupResponse {

        private Long id;
        private StatusAdocao statusAdocao;
        private String titulo;
        private String descricao;
        private String localizacao;
        private String foto;
    }

    @Data
    @AllArgsConstructor
    class UsuarioGroupResponse {

        private Long id;
        private String nome;
        private String email;
        private TipoUsuario tipoUsuario;
    }

    private OfertaAdocaoGroupResponse ofertaAdocao;
    private UsuarioGroupResponse anunciante;

    public OfertaAdocaoResponse(OfertaAdocao ofertaAdocao) throws SQLException {

        this.ofertaAdocao = new OfertaAdocaoGroupResponse(
                ofertaAdocao.getId(),
                ofertaAdocao.getStatusAdocao(),
                ofertaAdocao.getTitulo(),
                ofertaAdocao.getDescricao(),
                ofertaAdocao.getLocalizacao(),
                new String(ofertaAdocao.getAnimalAdocao().getFoto().getBytes(1, Math.toIntExact(ofertaAdocao.getAnimalAdocao().getFoto().length())))
        );

        this.anunciante = new UsuarioGroupResponse(
                ofertaAdocao.getDonoDaDivulgacao().getId(),
                ofertaAdocao.getDonoDaDivulgacao().getNome(),
                ofertaAdocao.getDonoDaDivulgacao().getEmail(),
                ofertaAdocao.getDonoDaDivulgacao().getTipoUsuario()
        );
    }
}
