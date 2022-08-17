package br.com.andersillva.gameflixusuarioapi.messagebroker.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class JogoLiberadoUsuarioDTO {

	private Long idUsuario;

	private List<JogoDTO> jogos = new ArrayList<>();

}
