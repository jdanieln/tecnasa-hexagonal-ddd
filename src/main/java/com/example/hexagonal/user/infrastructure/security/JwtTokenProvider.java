package com.example.hexagonal.user.infrastructure.security;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.Instant;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration-in-ms}")
    private long jwtExpirationInMs;

    public String generateToken(String username) {
        try {
            Instant now = Instant.now();

            JWTClaimsSet claims = new JWTClaimsSet.Builder()
                    .subject(username)
                    .issueTime(Date.from(now))
                    .expirationTime(Date.from(now.plusMillis(jwtExpirationInMs)))
                    .build();

            JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
            JWSObject jwsObject = new JWSObject(header, new Payload(claims.toJSONObject()));

            System.out.println("jwtSecret:" + jwtSecret);
            System.out.println("jwtExpirationInMs:" + jwtExpirationInMs);

            MACSigner signer = new MACSigner(jwtSecret);
            jwsObject.sign(signer);

            return jwsObject.serialize();
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate JWT token", e);
        }
    }

    public String getUsernameFromToken(String token) {
        try {
            JWSObject jwsObject = JWSObject.parse(token);
            JWTClaimsSet claims = JWTClaimsSet.parse(jwsObject.getPayload().toJSONObject());
            return claims.getSubject();
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse JWT token", e);
        }
    }

    public boolean validateToken(String token) {
        try {
            JWSObject jwsObject = JWSObject.parse(token);
            MACVerifier verifier = new MACVerifier(jwtSecret);
            return jwsObject.verify(verifier);
        } catch (JOSEException | ParseException e) {
            return false;
        }
    }
}
