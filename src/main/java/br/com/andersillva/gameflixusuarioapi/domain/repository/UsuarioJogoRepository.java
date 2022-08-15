package br.com.andersillva.gameflixusuarioapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.andersillva.gameflixusuarioapi.domain.model.UsuarioJogo;

@Repository
public interface UsuarioJogoRepository extends JpaRepository<UsuarioJogo, Long> {

}
