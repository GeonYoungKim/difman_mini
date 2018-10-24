package org.depromeet.fill_day.service;

import org.depromeet.fill_day.domain.AccountDetailsImpl;
import org.depromeet.fill_day.domain.entity.Account;
import org.depromeet.fill_day.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Autowired
    public UserDetailsServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> account = accountRepository.findByEmail(username);
        return account.map(AccountDetailsImpl::new)
                .orElseThrow(() ->
                new UsernameNotFoundException("가입되지 않은 이메일 주소이거나 잘못된 암호를 입력하셨습니다.")
            );
    }
}
