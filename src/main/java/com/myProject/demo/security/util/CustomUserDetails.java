package com.myProject.demo.security.util;

import com.myProject.demo.model.UserModel;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private UserModel userModel;
    private final GrantedAuthority authority;

    public CustomUserDetails(UserModel userModel, String role) {
        this.userModel = userModel;
        this.authority = new SimpleGrantedAuthority(role);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){

        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return userModel.getPassword();
    }

    @Override
    public String getUsername() {
        return userModel.getEmail();
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
