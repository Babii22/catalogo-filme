package br.com.catalogo.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public enum Role implements GrantedAuthority {
    ROLE_ADMIN("Admin"),
    ROLE_USER("User");

    @Getter
    private final String descricao;

    @Override
    public String getAuthority() {
        return name();
    }
}