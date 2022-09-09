package br.com.andersillva.gameflixusuarioapi.messaging.dto;

import br.com.andersillva.gameflixusuarioapi.domain.model.domaintype.FormaInclusao;
import lombok.Data;

@Data
public class MensagemEntregaUsuarioJogoDTO {

	private Long idJogo;

	private String nome;

	private FormaInclusao formaInclusao;

}
