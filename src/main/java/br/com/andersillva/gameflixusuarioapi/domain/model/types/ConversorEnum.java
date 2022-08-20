package br.com.andersillva.gameflixusuarioapi.domain.model.types;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ConversorEnum<T extends Enum<T> & EnumPersistente<E>, E> implements AttributeConverter<T, E> {

    private final Class<T> type;

    public ConversorEnum(Class<T> type) {
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
