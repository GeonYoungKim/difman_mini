package org.depromeet.fill_day.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.depromeet.fill_day.domain.dto.AccountDTO;
import org.depromeet.fill_day.domain.dto.SessionDTO;
import org.depromeet.fill_day.domain.entity.Account;
import org.depromeet.fill_day.exception.AuthenticationFailureException;
import org.depromeet.fill_day.exception.NoAccountFoundException;
import org.depromeet.fill_day.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    private static final String SECRET = "dePrOMEeTiSgREaT";
    private static final Algorithm AUTH_TOKEN_ENCODE_TYPE = Algorithm.HMAC256(SECRET);

    private AccountRepository accountRepository;
    private ObjectMapper objectMapper;

    @Autowired
    public AuthenticationService(AccountRepository accountRepository,
                                 ObjectMapper objectMapper) {
        this.accountRepository = accountRepository;
        this.objectMapper = objectMapper;
    }

    public AccountDTO join(AccountDTO newAccountDTO) {
        Account newAccount = objectMapper.convertValue(newAccountDTO, Account.class);
        Account createdAccount = accountRepository.save(newAccount);

        return objectMapper.convertValue(createdAccount, AccountDTO.class);
    }

    public UsernamePasswordAuthenticationToken signIn(String token) throws NoAccountFoundException,
            AuthenticationFailureException {
        String uid = null;

        try {
            final JWTVerifier jwtVerifier = JWT.require(AUTH_TOKEN_ENCODE_TYPE)
                    .build();
            DecodedJWT jwt = jwtVerifier.verify(token);
            uid = jwt.getSubject();
        } catch (JWTVerificationException exception){
            exception.printStackTrace();
            throw new AuthenticationFailureException();
        }

        if (uid == null) {
            throw new AuthenticationFailureException();
        }

        final Optional<Account> foundAccount = accountRepository.findById(uid);
        return foundAccount
                .map(account -> {
                    AccountDTO dto = objectMapper.convertValue(account, AccountDTO.class);

                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(dto,
                            null, null);

                    return authentication;
                })
                .orElseThrow(NoAccountFoundException::new);
    }

    public String generateToken(AccountDTO accountDTO) {
        String token = null;

        try {
            token = JWT.create()
                    .withSubject(accountDTO.getUid())
                    .sign(AUTH_TOKEN_ENCODE_TYPE);
        } catch (JWTCreationException exception){
            exception.printStackTrace();
        }

        return token;
    }
}
