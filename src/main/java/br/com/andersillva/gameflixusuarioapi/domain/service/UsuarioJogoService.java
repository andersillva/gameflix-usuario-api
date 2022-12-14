package br.com.andersillva.gameflixusuarioapi.domain.service;

import java.util.List;

import br.com.andersillva.gameflixusuarioapi.domain.model.UsuarioJogo;
import br.com.andersillva.gameflixusuarioapi.domain.model.domaintype.FormaInclusao;

public interface UsuarioJogoService {

	public List<UsuarioJogo> obterJogosUsuario(Long idUsuario);
	
	public void adicionarJogoUsuario(Long idUsuario, Long idJogo, String nome, FormaInclusao formaInclusao);

}
