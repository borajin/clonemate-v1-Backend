package com.ndex.clonemate.domain.goals.model;

import com.ndex.clonemate.global.utils.BaseEnumCode;
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
