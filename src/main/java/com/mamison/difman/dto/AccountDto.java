package com.mamison.difman.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountDto {

    private String email;
    private String name;

    @Builder
    public AccountDto(String email, String name) {
        this.email = email;
        this.name = name;
    }
}
