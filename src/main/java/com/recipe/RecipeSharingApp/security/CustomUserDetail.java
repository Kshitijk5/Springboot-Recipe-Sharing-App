package com.recipe.RecipeSharingApp.security;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;


@Component
public class CustomUserDetail implements UserDetails {

    private String username;
    private String email;
    private Collection<? extends GrantedAuthority> authorities;

    private String profileUrl;
    private String password;

    public CustomUserDetail(){

    }

    public CustomUserDetail(String username, String email,String profileUrl, String password, Collection<GrantedAuthority> authorities) {
        this.username = username;
        this.email = email;
        this.authorities = authorities;
        this.profileUrl = profileUrl;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
