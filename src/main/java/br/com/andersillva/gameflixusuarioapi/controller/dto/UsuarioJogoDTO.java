package br.com.andersillva.gameflixusuarioapi.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.andersillva.gameflixusuarioapi.domain.model.UsuarioJogo;
import lombok.Data;

@Data
public class UsuarioJogoDTO {

	private Long idJogo;

	public UsuarioJogoDTO(UsuarioJogo usuarioJogo) {
		this.idJogo = usuarioJogo.getIdJogo();
	}

	public static List<UsuarioJogoDTO> converter(List<UsuarioJogo> jogosUsuario) {
		return jogosUsuario.stream().map(UsuarioJogoDTO::new).collect(Collectors.toList());
	}

}
