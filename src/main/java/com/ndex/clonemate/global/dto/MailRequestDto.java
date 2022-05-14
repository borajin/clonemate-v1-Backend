package com.ndex.clonemate.global.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MailRequestDto {

    private static final String RESET_PASSWORD_SUBJECT = "clonemate의 비밀번호를 재설정하세요.";
    private static final String CERTIFICATION_SUBJECT = "clonemate의 이메일을 인증하세요.";

    private String email;
    private String subject;
    private String contents;

    public void writeResetPasswordMail(String url) {
        writeMail(RESET_PASSWORD_SUBJECT, getResetPasswordContents(url));
    }

    public void writeCertificationMail(String url) {
        writeMail(CERTIFICATION_SUBJECT, getCertificationContents(url));
    }

    private void writeMail(String subject, String contents) {
        this.subject = subject;
        this.contents = contents;
    }

    private String getResetPasswordContents(String url) {
        return String.format(
                "안녕하세요.<br><a href=\"%s\">다음 링크</a>를 통해 %s 계정의 clone mate 비밀번호를 재설정 하세요.<br>비밀번호 재설정을 요청하지 않았다면 이 메일을 무시하셔도 됩니다.<br>감사합니다.",
                url, email);
    }

    private String getCertificationContents(String url) {
        return String.format(
                "안녕하세요.<br><a href=\"%s\">다음 링크</a>를 통해 이메일 주소를 인증해 주세요.<br>이 주소의 인증을 요청하지 않았다면 이 메일을 무시하셔도 됩니다.<br>감사합니다.",
                url);
    }

    @Builder
    public MailRequestDto(String email, String subject, String contents) {
        this.email = email;
        this.subject = subject;
        this.contents = contents;
    }
}
