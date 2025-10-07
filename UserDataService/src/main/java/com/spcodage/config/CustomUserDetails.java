package com.spcodage.config;

import com.spcodage.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.Optional;
import java.util.Set;



@RequiredArgsConstructor
public class CustomUserDetails   implements UserDetails {

     private  final User user;
//    public Integer getId() {
//        return user.getUserId();  // expose userId for @PreAuthorize
//    }
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return user.getRoles() == null ? Collections.emptyList() :
//                user.getRoles()
//                        .stream()
//                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleName()))
//                        .collect(Collectors.toList());
//    }

    // for Adding Role
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Optional.ofNullable(user.getRoles())
                .orElse(Set.of())  // empty set if null
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleName()))
                .toList();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !Boolean.TRUE.equals(user.getIsDeleted());
       // return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !Boolean.TRUE.equals(user.getIsDeleted());
       // return !user.getIsDeleted();
    }
}



