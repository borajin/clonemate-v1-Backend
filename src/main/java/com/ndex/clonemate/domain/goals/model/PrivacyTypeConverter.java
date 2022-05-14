package com.ndex.clonemate.domain.goals.model;

import com.ndex.clonemate.global.utils.AbstractBaseEnumConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class PrivacyTypeConverter extends AbstractBaseEnumConverter {

    @Override
    protected PrivacyType[] getValues() {
        return PrivacyType.values();
    }
}
