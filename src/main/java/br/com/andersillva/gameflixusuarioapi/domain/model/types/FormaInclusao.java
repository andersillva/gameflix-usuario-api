package br.com.andersillva.gameflixusuarioapi.domain.model.types;

import lombok.Getter;

public enum FormaInclusao implements EnumPersistente<String> {

	COMPRA_AVULSA("C"),
	ASSINATURA("A");

	@Getter
	private String valor;

	private FormaInclusao(String valor) {
		this.valor = valor;
	}

    public static class Conversor extends ConversorEnum<FormaInclusao, String> {
        public Conversor() {
            super(FormaInclusao.class);
        }
    }

}
