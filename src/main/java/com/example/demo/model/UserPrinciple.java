package com.example.demo.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserPrinciple implements UserDetails {

    private Long ID;
    private String firstName;
    private String password;
    private String username;
    private Collection <?extends GrantedAuthority> authorities;

    public UserPrinciple (Long ID, String firstName, String password, String username, Collection<? extends GrantedAuthority> authorities) {
        this.ID = ID;
        this.firstName = firstName;
        this.password = password;
        this.username = username;
        this.authorities = authorities;
    }

    public static UserPrinciple create(User user){
        List<GrantedAuthority> authorityList = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());
        return new UserPrinciple(user.getId(),user.getFirstName(),user.getPassword(),user.getUsername(),authorityList);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities () {
        return this.authorities;
    }

    @Override
    public String getPassword () {
        return this.password;
    }

    @Override
    public String getUsername () {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired () {
        return true;
    }

    @Override
    public boolean isAccountNonLocked () {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired () {
        return true;
    }

    @Override
    public boolean isEnabled () {
        return true;
    }

    public Long getID () {
        return ID;
    }

    public void setID (Long ID) {
        this.ID = ID;
    }

    public String getFirstName () {
        return firstName;
    }

    public void setFirstName (String firstName) {
        this.firstName = firstName;
    }
}
