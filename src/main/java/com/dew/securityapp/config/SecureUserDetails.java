package com.dew.securityapp.config;

import com.dew.securityapp.entity.User;
import com.dew.securityapp.model.ERole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class SecureUserDetails implements UserDetails {
    private Long id;
    private String name;
    private String password;
    private String email;
    private String username;
    private List<GrantedAuthority> authorities;

    public SecureUserDetails(User user){
        id=user.getId();
        name=user.getUsername();
        password=user.getPassword();
        email=user.getEmail();
        username=user.getUsername();
        authorities= user.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority(ERole.ROLE_ADMIN.name()))
                   .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
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
        return false;
    }
}
