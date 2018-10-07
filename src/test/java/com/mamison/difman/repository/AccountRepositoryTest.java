package com.mamison.difman.repository;

import com.mamison.difman.entity.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void crud(){
        // create
        Account account = Account.builder()
                .email("c2619zz@naver.com")
                .useful(false)
                .build();

        Account savedAccount = accountRepository.save(account);

        assertThat(accountRepository.findAll().size()).isEqualTo(1);
        assertThat(savedAccount.getEmail()).isEqualTo("c2619zz@naver.com");

        // update
        savedAccount.setToken("geon");
        Account updatedAccount = accountRepository.save(savedAccount);

        assertThat(accountRepository.findAll().size()).isEqualTo(1);
        assertThat(updatedAccount.getToken()).isEqualTo("geon");

         //delete
        accountRepository.delete(savedAccount);
        assertThat(accountRepository.findAll().size()).isEqualTo(0);
    }
}
