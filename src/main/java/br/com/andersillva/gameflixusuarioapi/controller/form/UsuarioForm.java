package br.com.andersillva.gameflixusuarioapi.controller.form;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import br.com.andersillva.gameflixusuarioapi.domain.model.Usuario;
import lombok.Data;

@Data
public class UsuarioForm {

	private static ModelMapper mapper = new ModelMapper();

	@NotBlank
	private String nome;

	@NotBlank
	private String cpf;

	@NotNull
	private LocalDate dataNascimento;

	@NotBlank
	private String email;

	@NotBlank
	private String senha;

	public Usuario converter() {
		return mapper.map(this, Usuario.class);
	}

}
