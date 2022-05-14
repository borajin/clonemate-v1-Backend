package com.ndex.clonemate.domain.user.repository;

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

    private String introText;

    private char deleteYn;

    private LocalDateTime deleteDate;

    private char emailSearchYn;

    private char randomYn;

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

    public void update(String account, String email, String name, String introText,
        char emailSearchYn, char randomYn) {
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
        this.emailSearchYn=YES_FLAG;
        this.certifyYn=NO_FLAG;
        this.deleteYn=NO_FLAG;
    }

    private boolean isValidString(String str) {
        return str != null && !str.isEmpty();
    }

    private boolean isValidYn(char c) {
        return c == YES_FLAG || c == NO_FLAG;
    }
}
