package org.depromeet.fill_day.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.depromeet.fill_day.domain.dto.AccountDTO;
import org.depromeet.fill_day.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


public class TokenAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final AuthenticationService authenticationService;

    public TokenAuthenticationFilter(AuthenticationManager authenticationManager,
                                     AuthenticationService authenticationService) {
        this.authenticationManager = authenticationManager;
        this.authenticationService = authenticationService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            AccountDTO accountDTO = new ObjectMapper()
                    .readValue(req.getInputStream(), AccountDTO.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            accountDTO.getEmail(),
                            accountDTO.getAccessToken(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        AccountDTO accountDTO = AccountDTO.builder()
                .email(((User) auth.getPrincipal()).getUsername())
                .accessToken(((User) auth.getPrincipal()).getPassword())
                .build();

        String token = authenticationService.generateToken(accountDTO);

        res.addHeader("Authorization", String.format("Bearer %s", token));
    }
}
