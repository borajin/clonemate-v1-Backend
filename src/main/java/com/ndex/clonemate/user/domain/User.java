package com.ndex.clonemate.user.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name="users")
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

    @Column(name="password", nullable = false)
    private String password;

    @Column(name = "intro_text")
    private String introText;

    @Column(nullable = false, name = "email_search_yn")
    private char emailSearchYn;

    @Column(nullable = false, name = "random_yn")
    private char randomYn;

    @Builder
    public User(String account, String name, String email,
                String password, String introText, char emailSearchYn, char randomYn) {
        this.account = account;
        this.name = name;
        this.email = email;
        this.password = password;
        this.introText = introText;
        this.emailSearchYn = emailSearchYn;
        this.randomYn = randomYn;
    }
}
