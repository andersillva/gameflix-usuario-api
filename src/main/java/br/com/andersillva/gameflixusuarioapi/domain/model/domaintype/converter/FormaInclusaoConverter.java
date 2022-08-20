package br.com.andersillva.gameflixusuarioapi.domain.model.domaintype.converter;

import javax.persistence.Converter;

import br.com.andersillva.gameflixusuarioapi.domain.model.domaintype.FormaInclusao;

@Converter(autoApply = true)
public class FormaInclusaoConverter extends DominioAbstractConverter<FormaInclusao, String> {

    public FormaInclusaoConverter() {
        super(FormaInclusao.class);
    }

}
