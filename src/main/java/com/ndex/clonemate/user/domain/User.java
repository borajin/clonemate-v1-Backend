package com.ndex.clonemate.user.domain;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "users")
@DynamicInsert
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String account;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(name = "intro_text")
    private String introText;

    @Column(nullable = false, name = "delete_yn", columnDefinition = "character(1) default 'N'")
    private char deleteYn;

    @Column(name = "delete_date")
    private LocalDateTime deleteDate;

    @Column(nullable = false, name = "email_search_yn", columnDefinition = "character(1) default 'Y'")
    private char emailSearchYn;

    @Column(nullable = false, name = "random_yn", columnDefinition = "character(1) default 'Y'")
    private char randomYn;

    @Column(nullable = false, name = "certify_yn", columnDefinition = "character(1) default 'Y'")
    private char certifyYn;

    private final static char YES_FLAG = 'Y';
    private final static char NO_FLAG = 'N';

    public void changeDeleteFlag() {
        this.deleteYn = YES_FLAG;
        this.deleteDate = LocalDateTime.now();
    }

    public void changeCertifyFlag() {
        this.certifyYn = YES_FLAG;
    }

    public void update(String account, String email, String name, String introText, char emailSearchYn, char randomYn) {
        if (isValidString(account)) {
            this.account = account;
        }
        if (isValidString(email)) {
            this.email = email;
        }
        if (isValidString(name)) {
            this.name = name;
        }
        if (isValidString(introText)) {
            this.introText = introText;
        }
        if (isValidYn(emailSearchYn)) {
            this.emailSearchYn = emailSearchYn;
        }
        if (isValidYn(randomYn)) {
            this.randomYn = randomYn;
        }
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void encodePassword(PasswordEncoder encoder) {
        this.password = encoder.encode(this.password);
    }

    public boolean isNotDeletedUser() {
        return this.deleteYn == NO_FLAG;
    }

    public boolean isNotCertifiedUser() {
        return this.certifyYn == NO_FLAG;
    }

    @Builder
    public User(String account, String name, String email,
                String password, String introText) {
        this.account = account;
        this.name = name;
        this.email = email;
        this.password = password;
        this.introText = introText;

        //ERROR: invalid byte sequence for encoding "UTF8": 0x00 에러 해결 위해 직접 초기화
        this.certifyYn=YES_FLAG;
        this.deleteYn=NO_FLAG;
        this.emailSearchYn=YES_FLAG;
        this.randomYn=YES_FLAG;
    }

    private boolean isValidString(String str) {
        return str != null && !str.isEmpty();
    }

    private boolean isValidYn(char c) {
        return c == YES_FLAG || c == NO_FLAG;
    }
}