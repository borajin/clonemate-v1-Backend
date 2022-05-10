package com.ndex.clonemate.goal.domain;

import com.ndex.clonemate.utils.AbstractBaseEnumConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class PrivacyTypeConverter extends AbstractBaseEnumConverter {

    @Override
    protected PrivacyType[] getValues() {
        return PrivacyType.values();
    }
}
