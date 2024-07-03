package com.carrefour.customer.delivery.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.carrefour.customer.delivery.enums.ExceptionDeliveryType;
import com.carrefour.customer.delivery.exceptions.TechnicalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtExtract {

    private final JwtProperties properties;

    public DecodedJWT decode(String token) throws TechnicalException {
        DecodedJWT decodedJWT = null;
        try {
            decodedJWT = JWT.require(Algorithm.HMAC256(properties.getSecretKey()))
                    .build()
                    .verify(token);
        } catch(JWTDecodeException ex) {
            throw new TechnicalException("Erreur survenue lors du decode du JWT", ExceptionDeliveryType.TOKEN_INVALID_EXCEPTION);
        }
        return decodedJWT;
    }
}
