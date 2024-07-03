package com.carrefour.customer.delivery.mappers;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.carrefour.customer.delivery.dto.PrincipaleUser;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtToCustomerMapper {

    public PrincipaleUser convert(DecodedJWT jwt) {
        return PrincipaleUser.builder()
                .id(Long.valueOf(jwt.getSubject()))
                .email(jwt.getClaim("u").asString())
                .authorities(extractAuthorities(jwt))
                .build();
    }

    private List<SimpleGrantedAuthority> extractAuthorities(DecodedJWT jwt) {
        var claim = jwt.getClaim("r");
        if (claim.isNull() || claim.isMissing()) return List.of();
        return claim.asList(SimpleGrantedAuthority.class);
    }
}
