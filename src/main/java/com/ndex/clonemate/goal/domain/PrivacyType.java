package com.ndex.clonemate.goal.domain;

import com.ndex.clonemate.utils.BaseEnumCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PrivacyType implements BaseEnumCode<String> {
    PUBLIC("PUBLIC"),
    FOLLOWINGS("FOLLOWINGS"),
    PRIVATE("PRIVATE"),
    HIDDEN("HIDDEN");

    private final String value;
}
