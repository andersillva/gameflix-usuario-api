package br.com.andersillva.gameflixusuarioapi.domain.service;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.andersillva.gameflixusuarioapi.domain.model.Usuario;
import br.com.andersillva.gameflixusuarioapi.domain.model.UsuarioJogo;
import br.com.andersillva.gameflixusuarioapi.domain.model.domaintype.FormaInclusao;
import br.com.andersillva.gameflixusuarioapi.domain.repository.UsuarioJogoRepository;

@Service
public class UsuarioJogoServiceImpl implements UsuarioJogoService {

	@Autowired
	private UsuarioJogoRepository usuarioJogoRepository;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private Clock clock;

	@Override
	public List<UsuarioJogo> obterJogosUsuario(Long idUsuario) {
		return usuarioJogoRepository.obterJogosUsuario(idUsuario);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void adicionarJogoUsuario(Long idUsuario, Long idJogo, String nome, FormaInclusao formaInclusao) {

		Usuario usuario = usuarioService.obterPorId(idUsuario);

		UsuarioJogo usuarioJogo = new UsuarioJogo();
		usuarioJogo.setUsuario(usuario);
		usuarioJogo.setIdJogo(idJogo);
		usuarioJogo.setNome(nome);
		usuarioJogo.setDataInclusao(LocalDate.now(clock));
		usuarioJogo.setFormaInclusao(formaInclusao);
		usuarioJogoRepository.saveAndFlush(usuarioJogo);

	}

}
