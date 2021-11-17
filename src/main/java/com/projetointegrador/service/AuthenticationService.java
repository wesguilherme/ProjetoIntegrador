package com.projetointegrador.service;

import com.projetointegrador.entity.User;
import com.projetointegrador.repository.UserPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserPersistence persistence;

    public AuthenticationService(UserPersistence persistence) {
        this.persistence = persistence;
    }

    /**
     * @param username
     * @return  efetua a autenticação dos usuários
     * @author - Grupo 5
     * @throws UsernameNotFoundException  retorna exceção caso usuário não esteja cadastrado
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = persistence.findByUser(username);
        return user;
    }
}

