package com.projetointegrador.config;

import com.projetointegrador.entity.User;
import com.projetointegrador.repository.UserPersistence;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenFilterAuthentication extends OncePerRequestFilter {

    private TokenService tokenService;
    private UserPersistence persistence;

    public TokenFilterAuthentication(TokenService tokenService, UserPersistence persistence) {
        this.tokenService = tokenService;
        this.persistence = persistence;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        //obtem token do cabecalho da requisicao
        String token = extraiToken(request);
        if (token != null) {
            //validar token
            boolean tokenValido = tokenService.tokenValido(token);

            if (tokenValido) {
                //autenticar o token
                realizaAutenticacaoDoTokenNoSpring(token);
            }
        }
        filterChain.doFilter(request, response);
    }

    private void realizaAutenticacaoDoTokenNoSpring(String token) {
        String userName = tokenService.getUsername(token);
        User user = this.persistence.findByUser(userName);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication); //forçando autenticacao pelo spring
    }

    private String extraiToken(HttpServletRequest request) {
        String token = "";
        String authorization = request.getHeader("Authorization");
        if (authorization == null || authorization.isEmpty() || !authorization.startsWith("Bearer ")) {
            return null;
        } else {
            token = authorization.substring(7, authorization.length());
        }
        return token;
    }
}
