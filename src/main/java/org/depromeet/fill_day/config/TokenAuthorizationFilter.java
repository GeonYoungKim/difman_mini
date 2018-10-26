package org.depromeet.fill_day.config;

import org.depromeet.fill_day.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class TokenAuthorizationFilter extends BasicAuthenticationFilter {

    private final AuthenticationService authenticationService;

    public TokenAuthorizationFilter(AuthenticationManager authManager,
                                    AuthenticationService authenticationService) {
        super(authManager);
        this.authenticationService = authenticationService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {

        final String authorizationHeaderValue = req.getHeader("Authorization");
        if (authorizationHeaderValue == null || !authorizationHeaderValue.startsWith("Bearer ")) {
            chain.doFilter(req, res);
            return ;
        }

        String[] splittedValues = authorizationHeaderValue.split(" ");

        UsernamePasswordAuthenticationToken authentication = authenticationService.signIn(splittedValues[1]);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // logger.debug("Logging in with {}", authentication.getPrincipal());
        chain.doFilter(req, res);
    }
}
