package br.com.andersillva.gameflixusuarioapi.domain.service;

import java.util.List;

import br.com.andersillva.gameflixusuarioapi.domain.model.UsuarioJogo;

public interface UsuarioJogoService {

	public List<UsuarioJogo> obterJogosUsuario(Long idUsuario);
	
	public void adicionarJogoUsuario(Long idJogo, Long idUsuario);

}
