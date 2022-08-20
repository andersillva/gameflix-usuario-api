package br.com.andersillva.gameflixusuarioapi.domain.model.domaintype;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public enum FormaInclusao implements Dominio<String> {

	COMPRA_AVULSA("C"),
	ASSINATURA("A");

	@Getter
	private String valor;

}
