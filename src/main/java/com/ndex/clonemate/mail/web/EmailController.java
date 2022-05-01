package com.ndex.clonemate.mail.web;

import com.ndex.clonemate.mail.service.EmailService;
import com.ndex.clonemate.mail.web.dto.UserPasswordRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;

    @GetMapping("/users/email/certify/{id}")
    public ResponseEntity<?> sendCertifyMail(@PathVariable long id) {
        return emailService.sendCertifyMail(id);
    }

    @GetMapping("/users/email/{id}/{token}")
    public ResponseEntity<?> checkCertifyMail(@PathVariable Long id, @PathVariable String token) {
        return emailService.checkCertifyMail(id, token);
    }

    @GetMapping("/users/email/reset/{id}")
    public ResponseEntity<?> sendResetPasswordMail(@PathVariable long id) {
        return emailService.sendResetPasswordMail(id);
    }

    @PutMapping("/users/email/password")
    public ResponseEntity<?> changePassword(@RequestBody UserPasswordRequestDto request) {
        return emailService.changePassword(request);
    }
}
