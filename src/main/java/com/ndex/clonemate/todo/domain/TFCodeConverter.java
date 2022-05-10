package com.ndex.clonemate.todo.domain;

import com.ndex.clonemate.utils.AbstractBaseEnumConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class TFCodeConverter extends AbstractBaseEnumConverter {

    @Override
    protected TFCode[] getValues() {
        return TFCode.values();
    }
}
