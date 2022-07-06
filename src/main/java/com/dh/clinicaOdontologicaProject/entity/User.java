package com.dh.clinicaOdontologicaProject.entity;
import com.dh.clinicaOdontologicaProject.loginSecurity.AppUserRoles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Long id;
    @Column
    private String name;
    @Column
    private String userName;
    @Column
    private String email;
    @Column
    private String password;
    @Enumerated(EnumType.STRING)
    private AppUserRoles userRoles;

    public User(){}
    public User(String name, String userName, String email, String password, AppUserRoles userRoles) {
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.userRoles = userRoles;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AppUserRoles getUserRole() {
        return userRoles;
    }

    public void setUserRole(AppUserRoles userRoles) {
        this.userRoles = userRoles;
    }

    //UserDetails methods
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userRoles.name());
        return Collections.singletonList(grantedAuthority);
    }

    @Override
    public String getUsername() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
