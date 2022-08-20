package br.com.andersillva.gameflixusuarioapi.domain.model.domaintype.converter;

import javax.persistence.AttributeConverter;

import br.com.andersillva.gameflixusuarioapi.domain.model.domaintype.Dominio;

public abstract class DominioAbstractConverter<T extends Enum<T> & Dominio<E>, E> implements AttributeConverter<T, E> {

    private final Class<T> type;

    protected DominioAbstractConverter(Class<T> type) {
        this.type = type;
    }

    @Override
    public E convertToDatabaseColumn(T valorEnum) {
        return valorEnum != null ? valorEnum.getValor() : null;
    }

    @Override
    public T convertToEntityAttribute(E valorPersistido) {

        T[] enums = type.getEnumConstants();

        for (T e : enums) {
            if (e.getValor().equals(valorPersistido)) {
                return e;
            }
        }

        throw new IllegalArgumentException();
    }

}
