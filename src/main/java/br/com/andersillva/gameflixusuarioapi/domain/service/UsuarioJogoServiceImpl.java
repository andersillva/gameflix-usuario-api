package br.com.andersillva.gameflixusuarioapi.domain.service;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
		Optional<UsuarioJogo> usuarioJogo = usuarioJogoRepository.obterJogoUsuario(idUsuario, idJogo);

		if (usuarioJogo.isPresent()) {
			UsuarioJogo usuarioJogoExistente = usuarioJogo.get();
			if ((usuarioJogoExistente.getFormaInclusao().equals(FormaInclusao.ASSINATURA))&&(formaInclusao.equals(FormaInclusao.COMPRA_AVULSA))) {
				usuarioJogoExistente.setFormaInclusao(FormaInclusao.COMPRA_AVULSA);
				usuarioJogoExistente.setDataInclusao(LocalDate.now(clock));
				usuarioJogoRepository.saveAndFlush(usuarioJogoExistente);
			}
		} else {
			UsuarioJogo usuarioJogoNovo = new UsuarioJogo();
			usuarioJogoNovo.setUsuario(usuario);
			usuarioJogoNovo.setIdJogo(idJogo);
			usuarioJogoNovo.setNome(nome);
			usuarioJogoNovo.setDataInclusao(LocalDate.now(clock));
			usuarioJogoNovo.setFormaInclusao(formaInclusao);
			usuarioJogoRepository.saveAndFlush(usuarioJogoNovo);
		}

	}

}
