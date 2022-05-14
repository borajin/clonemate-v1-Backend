package com.ndex.clonemate.global.utils;

import java.util.Arrays;
import javax.persistence.AttributeConverter;

public abstract class AbstractBaseEnumConverter<X extends Enum<X> & BaseEnumCode<Y>, Y> implements
    AttributeConverter<X, Y> {

    protected abstract X[] getValues();

    @Override
    public Y convertToDatabaseColumn(X attribute) {
        return attribute.getValue();
    }

    @Override
    public X convertToEntityAttribute(Y dbData) {
        return Arrays.stream(getValues()).filter(e -> e.getValue().equals(dbData)).findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Unspported type"));
    }
}
