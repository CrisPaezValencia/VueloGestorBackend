package com.jcaa.hexagonal.entrypoint.rest.v1.common;

import com.jcaa.hexagonal.core.domain.model.Rol;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class AuthenticatedUser implements UserDetails {

    private final Long usuarioId;
    private final String username;
    private final Rol rol;
    private final Long clienteId;
    private final Long pilotoId;
    private final List<SimpleGrantedAuthority> authorities;

    public AuthenticatedUser(Long usuarioId, String username, Rol rol, Long clienteId, Long pilotoId) {
        this.usuarioId = usuarioId;
        this.username = username;
        this.rol = rol;
        this.clienteId = clienteId;
        this.pilotoId = pilotoId;
        this.authorities = List.of(new SimpleGrantedAuthority("ROLE_" + rol.name()));
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public Rol getRol() {
        return rol;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public Long getPilotoId() {
        return pilotoId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}