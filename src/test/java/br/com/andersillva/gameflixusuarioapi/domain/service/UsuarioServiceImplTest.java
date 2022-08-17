package br.com.andersillva.gameflixusuarioapi.domain.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.andersillva.gameflixusuarioapi.domain.model.Usuario;
import br.com.andersillva.gameflixusuarioapi.domain.repository.UsuarioRepository;
import br.com.andersillva.gameflixusuarioapi.domain.service.exception.RegistroDuplicadoException;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceImplTest {

	@Mock
	private UsuarioRepository usuarioRepository;

	@Mock
	private PasswordEncoder passwordEncoder;
	
	@InjectMocks
	private UsuarioService usuarioService = new UsuarioServiceImpl();
	
	@Test
	@DisplayName("Testa o registro de um usuário.")
	void deverRegistrarUsuario() {

		Usuario usuario = obterUsuarioTeste();

		Mockito.when(usuarioRepository.saveAndFlush(Mockito.any())).thenReturn(usuario);
		Mockito.when(usuarioRepository.obterPorEmail(Mockito.any())).thenReturn(Optional.empty());
		Mockito.when(usuarioRepository.obterPorCpf(Mockito.any())).thenReturn(Optional.empty());

		usuarioService.registrar(usuario);
		Mockito.verify(usuarioRepository).saveAndFlush(usuario);

	}

	@Test()
	void naoDeveRegistrarUsuarioComEmailJaCadastrado() {

		Usuario umUsuarioNovo = obterUsuarioTeste();
		Usuario outroUsuarioComMesmoEmail = obterUsuarioTeste();

		Mockito.when(usuarioRepository.obterPorEmail(Mockito.any()))
			.thenReturn(Optional.of(outroUsuarioComMesmoEmail));

		assertThatThrownBy(() -> usuarioService.registrar(umUsuarioNovo))
			.isInstanceOf(RegistroDuplicadoException.class);

	}

	@Test
	void naoDeveRegistrarUsuarioComCpfJaCadastrado() {

		Usuario umUsuarioNovo = obterUsuarioTeste();
		Usuario outroUsuarioComMesmoCpf = obterUsuarioTeste();

		Mockito.when(usuarioRepository.obterPorEmail(Mockito.any()))
			.thenReturn(Optional.of(outroUsuarioComMesmoCpf));

		assertThatThrownBy(() -> usuarioService.registrar(umUsuarioNovo))
			.isInstanceOf(RegistroDuplicadoException.class);

	}

	private Usuario obterUsuarioTeste() {
		Usuario usuario = new Usuario();
		usuario.setNome("Fulano de Tal");
		usuario.setCpf("76068734374");
		usuario.setEmail("fulanodetal@email.com");
		usuario.setSenha("abc123");
		return usuario;
	}
}
