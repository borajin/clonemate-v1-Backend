package com.ndex.clonemate.todo.domain;

import com.ndex.clonemate.utils.BaseEnumCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TFCode implements BaseEnumCode<String> {
    TRUE("T", true),
    FALSE("F", false);

    private final String value;
    private final boolean boolValue;

    public static TFCode boolValueOf(boolean value) {
        if (value == TRUE.boolValue) {
            return TRUE;
        } else {
            return FALSE;
        }
    }
}
