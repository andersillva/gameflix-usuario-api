package br.com.andersillva.gameflixusuarioapi.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.andersillva.gameflixusuarioapi.domain.model.UsuarioJogo;

@Repository
public interface UsuarioJogoRepository extends JpaRepository<UsuarioJogo, Long> {

	@Query("select uj from UsuarioJogo uj where uj.usuario.id = :idUsuario")
	public List<UsuarioJogo> obterJogosUsuario(Long idUsuario);

	@Query("select uj from UsuarioJogo uj where uj.usuario.id = :idUsuario and uj.idJogo = :idJogo")
	public Optional<UsuarioJogo> obterJogoUsuario(Long idUsuario, Long idJogo);

}
