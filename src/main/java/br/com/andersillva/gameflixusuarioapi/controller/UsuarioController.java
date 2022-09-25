package br.com.andersillva.gameflixusuarioapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.andersillva.gameflixusuarioapi.controller.dto.TokenDTO;
import br.com.andersillva.gameflixusuarioapi.controller.dto.UsuarioDTO;
import br.com.andersillva.gameflixusuarioapi.controller.dto.UsuarioJogoDTO;
import br.com.andersillva.gameflixusuarioapi.controller.form.AutenticacaoForm;
import br.com.andersillva.gameflixusuarioapi.controller.form.UsuarioForm;
import br.com.andersillva.gameflixusuarioapi.controller.util.VersaoAPI;
import br.com.andersillva.gameflixusuarioapi.domain.model.Usuario;
import br.com.andersillva.gameflixusuarioapi.domain.model.UsuarioJogo;
import br.com.andersillva.gameflixusuarioapi.domain.service.UsuarioJogoService;
import br.com.andersillva.gameflixusuarioapi.domain.service.UsuarioService;
import br.com.andersillva.gameflixusuarioapi.security.TokenService;

@RestController
@RequestMapping(path=VersaoAPI.URI_BASE_V1, produces=MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioJogoService usuarioJogoService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenService tokenService;

	@GetMapping("/{id}/jogos")
	public ResponseEntity<List<UsuarioJogoDTO>> obterJogosUsuario(@PathVariable("id") Long idUsuario) {
		List<UsuarioJogo> jogosUsuario = usuarioJogoService.obterJogosUsuario(idUsuario);
		if (!jogosUsuario.isEmpty()) {
			return new ResponseEntity<>(UsuarioJogoDTO.converter(jogosUsuario), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsuarioDTO> registrar(@Valid @RequestBody UsuarioForm usuarioForm) {

		Usuario usuario = usuarioForm.converter();
		usuarioService.registrar(usuario);
		return new ResponseEntity<>(new UsuarioDTO(usuario), HttpStatus.CREATED);
	}

	@PostMapping(path="/login", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TokenDTO> autenticar(@RequestBody @Validated AutenticacaoForm autenticacaoForm){
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(autenticacaoForm.getEmail(), autenticacaoForm.getSenha());
		Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		String token = tokenService.generateToken(authentication);
		return ResponseEntity.ok(TokenDTO.builder().type("Bearer").token(token).build());
	}


}
