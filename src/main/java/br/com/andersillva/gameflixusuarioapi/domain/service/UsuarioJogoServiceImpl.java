package br.com.andersillva.gameflixusuarioapi.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.andersillva.gameflixusuarioapi.domain.model.UsuarioJogo;

@Service
public class UsuarioJogoServiceImpl implements UsuarioJogoService {

	@Override
	public List<UsuarioJogo> obterJogosUsuario(Long idUsuario) {
		return null;
	}

	@Override
	public void adicionarJogoUsuario(Long idJogo, Long idUsuario) {

	}

}
