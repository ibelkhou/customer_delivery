package com.carrefour.customer.delivery.config;

import com.carrefour.customer.delivery.dto.PrincipaleUser;
import org.springframework.security.authentication.AbstractAuthenticationToken;

public class PrincipaleAuthentificationToken extends AbstractAuthenticationToken {

    private final PrincipaleUser principale;
    public PrincipaleAuthentificationToken(PrincipaleUser principale) {
        super(principale.getAuthorities());
        this.principale = principale;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public PrincipaleUser getPrincipal() {
        return principale;
    }
}
