package br.com.andersillva.gameflixusuarioapi.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.andersillva.gameflixusuarioapi.domain.model.Usuario;
import br.com.andersillva.gameflixusuarioapi.domain.repository.UsuarioRepository;
import br.com.andersillva.gameflixusuarioapi.domain.service.exception.RegistroDuplicadoException;
import br.com.andersillva.gameflixusuarioapi.domain.service.exception.RegistroNaoEncontradoException;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void registrar(Usuario usuario) {

		validarDuplicidade(usuario);
		usuario.setId(null);
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		usuarioRepository.saveAndFlush(usuario);

	}

	private void validarDuplicidade(Usuario usuario) {

		if (usuarioRepository.obterPorEmail(usuario.getEmail()).isPresent())
			throw new RegistroDuplicadoException();

		if (usuarioRepository.obterPorCpf(usuario.getCpf()).isPresent())
			throw new RegistroDuplicadoException();

	}

	@Override
	public Usuario obterPorId(Long idUsuario) {
		Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
		return usuario.orElseThrow(RegistroNaoEncontradoException::new);
	}

}
