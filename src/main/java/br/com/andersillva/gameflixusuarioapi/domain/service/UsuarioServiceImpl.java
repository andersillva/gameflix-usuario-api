package br.com.andersillva.gameflixusuarioapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.andersillva.gameflixusuarioapi.domain.model.Usuario;
import br.com.andersillva.gameflixusuarioapi.domain.repository.UsuarioRepository;
import br.com.andersillva.gameflixusuarioapi.domain.service.exception.RegistroDuplicadoException;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void registrar(Usuario usuario) {

		usuario.setId(null);
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		salvar(usuario);

	}

	private void salvar(Usuario usuario) {

		try {
			usuarioRepository.saveAndFlush(usuario);
		} catch (DataIntegrityViolationException e) {
			throw new RegistroDuplicadoException();
		}

	}

}
