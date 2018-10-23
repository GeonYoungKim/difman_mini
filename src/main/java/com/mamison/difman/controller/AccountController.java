package com.mamison.difman.controller;

import com.google.gson.Gson;
import com.mamison.difman.dto.AccountDto;
import com.mamison.difman.entity.Account;
import com.mamison.difman.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/daily")
public class AccountController {

    private final AccountRepository accountRepository;
    private final Gson gson;

    @PostMapping("/join")
    public String join(@RequestBody AccountDto accountDto) {
        if (accountRepository.getOne(accountDto.getEmail()) != null){
            return gson.toJson(new Account());
        }
        Account account = Account.builder()
                .email(accountDto.getEmail())
                .name(accountDto.getName())
                .build();
        Account savedAccount = accountRepository.saveAndFlush(account);
        return gson.toJson(savedAccount);
    }


}
