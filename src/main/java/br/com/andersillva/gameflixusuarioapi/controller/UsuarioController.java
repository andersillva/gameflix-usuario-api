package br.com.andersillva.gameflixusuarioapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.andersillva.gameflixusuarioapi.controller.dto.TokenDTO;
import br.com.andersillva.gameflixusuarioapi.controller.form.AutenticacaoForm;
import br.com.andersillva.gameflixusuarioapi.controller.form.UsuarioForm;
import br.com.andersillva.gameflixusuarioapi.controller.util.VersaoAPI;
import br.com.andersillva.gameflixusuarioapi.domain.model.Usuario;
import br.com.andersillva.gameflixusuarioapi.domain.service.UsuarioService;
import br.com.andersillva.gameflixusuarioapi.security.TokenService;

@RestController
@RequestMapping(path=VersaoAPI.URI_BASE_V1, produces=MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenService tokenService;

	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> registrar(@Valid @RequestBody UsuarioForm usuarioForm) {

		Usuario usuario = usuarioForm.converter();
		usuarioService.registrar(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).build();

	}

	@PostMapping(path="/login", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TokenDTO> autenticar(@RequestBody @Validated AutenticacaoForm autenticacaoForm){
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(autenticacaoForm.getEmail(), autenticacaoForm.getSenha());
		Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		String token = tokenService.generateToken(authentication);
		return ResponseEntity.ok(TokenDTO.builder().type("Bearer").token(token).build());
	}

}
