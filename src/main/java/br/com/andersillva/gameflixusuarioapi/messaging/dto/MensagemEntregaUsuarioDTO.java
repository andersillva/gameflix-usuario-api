package br.com.andersillva.gameflixusuarioapi.messaging.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.andersillva.gameflixusuarioapi.domain.model.Usuario;
import br.com.andersillva.gameflixusuarioapi.domain.model.UsuarioJogo;
import lombok.Data;

@Data
public class MensagemEntregaUsuarioDTO {

	private Long idUsuario;

	private List<MensagemEntregaUsuarioJogoDTO> jogos = new ArrayList<>();

	public UsuarioJogo converter() {
		Usuario usuario = new Usuario();
		usuario.setId(this.idUsuario);
		UsuarioJogo usuarioJogo = new UsuarioJogo();
		usuarioJogo.setUsuario(usuario);
		return usuarioJogo;
	}
}
