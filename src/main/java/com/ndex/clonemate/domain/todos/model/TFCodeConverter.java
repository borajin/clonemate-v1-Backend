package com.ndex.clonemate.domain.todos.model;

import com.ndex.clonemate.global.utils.AbstractBaseEnumConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class TFCodeConverter extends AbstractBaseEnumConverter {

    @Override
    protected TFCode[] getValues() {
        return TFCode.values();
    }
}
