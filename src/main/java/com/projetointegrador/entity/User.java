package com.projetointegrador.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class User implements UserDetails {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    private String user;

    private String password;

    private boolean enabled;

    public User() {
    }

    public User(String user, String password, boolean enabled) {
        this.user = user;
        this.password = password;
        this.enabled = enabled;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Profile> profiles;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        this.profiles.forEach(profile -> authorities.add(new SimpleGrantedAuthority(profile.getName())));
        return authorities;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getPassword() {

        return this.password;
    }

    @Override
    public String getUsername() {
        return this.user;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}