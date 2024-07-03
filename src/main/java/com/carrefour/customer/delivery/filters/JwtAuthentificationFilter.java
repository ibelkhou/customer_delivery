package com.carrefour.customer.delivery.filters;

import com.carrefour.customer.delivery.config.JwtExtract;
import com.carrefour.customer.delivery.config.PrincipaleAuthentificationToken;
import com.carrefour.customer.delivery.exceptions.TechnicalException;
import com.carrefour.customer.delivery.mappers.JwtToCustomerMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthentificationFilter extends OncePerRequestFilter {

    private final JwtExtract jwtExtract;
    private final JwtToCustomerMapper jwtToCustomerMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        extractTokenFromRequest(request)
                .map(jwt -> {
                    try {
                        return jwtExtract.decode(jwt);
                    } catch(TechnicalException ex) {
                        return null;
                    }
                })
                .map(jwtToCustomerMapper::convert)
                .map(PrincipaleAuthentificationToken::new)
                .ifPresent(authentification -> SecurityContextHolder.getContext().setAuthentication(authentification));

        filterChain.doFilter(request, response);
    }

    private Optional<String> extractTokenFromRequest(HttpServletRequest request) {
        var token = request.getHeader("Authorization");
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return Optional.of(token.substring(7));
        }

        return Optional.empty();
    }
}
