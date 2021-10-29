package com.projetointegrador.request;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


public class LoginRequest {

    private String user;
    private String senha;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(user, senha);
    }


}