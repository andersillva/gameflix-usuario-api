package br.com.andersillva.gameflixusuarioapi.controller.form;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;

import br.com.andersillva.gameflixusuarioapi.domain.model.Usuario;
import lombok.Setter;

@Setter
public class UsuarioForm {

	private static final ModelMapper mapper = new ModelMapper();

	private String nome;

	private String cpf;

	private LocalDate dataNascimento;

	private String email;

	private String senha;

	public Usuario converter() {
		Usuario usuario = mapper.map(this, Usuario.class);
		return usuario;
	}

}
