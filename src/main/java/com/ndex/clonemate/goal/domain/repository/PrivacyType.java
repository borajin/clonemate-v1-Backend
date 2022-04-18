package com.ndex.clonemate.goal.domain.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;

//enum을 사용하면 이 필드에 어떤 값만 허용 가능한지 (열거형인지) 확실하게 해줌.
@AllArgsConstructor
@Getter
public enum PrivacyType {
    PUB("PUBLIC"),
    FOL("FOLLOWINGS"),
    PRI("PRIVATE"),
    HID("HIDDEN");

    private final String fullName;

    //COMPLETE : 이 방법이 아닌 거 같은데.. 다시 알아보기 -> switch 문/else 지양하기. for문이나 if문으로 구현.
    public static PrivacyType of(String privacy) {
        if (privacy.equals(PrivacyType.PUB.getFullName())) return PrivacyType.PUB;
        else if (privacy.equals(PrivacyType.FOL.getFullName())) return PrivacyType.FOL;
        else if (privacy.equals(PrivacyType.PRI.getFullName())) return PrivacyType.PRI;
        else if (privacy.equals(PrivacyType.HID.getFullName())) return PrivacyType.HID;
        else throw new IllegalArgumentException("Invalid value : " + privacy);
    }
}
