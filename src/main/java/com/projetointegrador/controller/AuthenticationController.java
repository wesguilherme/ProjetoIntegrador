package com.projetointegrador.controller;

import com.projetointegrador.config.TokenService;
import com.projetointegrador.dto.TokenDto;
import com.projetointegrador.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;

    @PostMapping(value = "/auth")
    public ResponseEntity<TokenDto> realizaAutenticacao(@RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken dadosLogin = loginRequest.converter();
        try {
            Authentication authentication = manager.authenticate(dadosLogin);
            String token = tokenService.geraToken(authentication);
            return ResponseEntity.ok(new TokenDto(token, "Bearer"));
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}