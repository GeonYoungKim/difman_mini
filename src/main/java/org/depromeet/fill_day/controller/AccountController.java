package org.depromeet.fill_day.controller;

import org.depromeet.fill_day.domain.dto.AccountDTO;
import org.depromeet.fill_day.domain.dto.SessionDTO;
import org.depromeet.fill_day.exception.AuthenticationFailureException;
import org.depromeet.fill_day.exception.NoAccountFoundException;
import org.depromeet.fill_day.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/accounts")
public class AccountController {

    private final AuthenticationService authenticationService;

    public AccountController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public ResponseEntity<AccountDTO> join(@RequestBody AccountDTO accountDTO) {
        return ResponseEntity.ok(authenticationService.join(accountDTO));
    }

}
