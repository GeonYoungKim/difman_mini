package org.depromeet.fill_day.repository;

import org.depromeet.fill_day.domain.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    public Optional<Account> findByEmail(String email);
}
