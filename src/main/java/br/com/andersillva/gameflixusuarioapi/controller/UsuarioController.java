package br.com.andersillva.gameflixusuarioapi.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.andersillva.gameflixusuarioapi.controller.form.UsuarioForm;
import br.com.andersillva.gameflixusuarioapi.controller.util.VersaoAPI;
import br.com.andersillva.gameflixusuarioapi.domain.model.Usuario;
import br.com.andersillva.gameflixusuarioapi.domain.service.UsuarioService;

@RestController
@RequestMapping(path=VersaoAPI.URI_BASE_V1, produces=MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController {

	private UsuarioService usuarioService;
	
	@PostMapping(path="/clubes", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> incluirClube(@Valid @RequestBody UsuarioForm usuarioForm) {

		Usuario usuario = usuarioForm.converter();
		usuarioService.registrar(usuario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();

		return ResponseEntity.created(uri).build();

	}
}
