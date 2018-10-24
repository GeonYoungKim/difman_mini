package org.depromeet.fill_day.repository;

import org.depromeet.fill_day.domain.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {
    public Optional<Account> findByEmail(String email);
}
